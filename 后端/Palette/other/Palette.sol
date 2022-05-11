// SPDX-License-Identifier: SimPL-2.0
pragma solidity ^0.4.0;

interface BI{
    /* @dev 当MTB的任何所有权发送改变时，触发此事件 */
    event Transaction(address indexed _from, address indexed _to, uint indexed _num);
    /* @dev 当MTB的任何总数量增加时，触发此事件 */
    event IncreaseCirculation(uint indexed _num);
    /* @dev 当MTB的任何总数量减少时，触发此事件 */
    event ReduceCirculation(uint indexed _num);

}

/// 该智能合约为喵凸币（MTB）的智能合同
/// 主管喵凸币的各种方法和保存其相关信息
contract MTB is BI{

    address public creator;        // 创造者
    address public admin;          // 管理员
    string public name;        // 币种名称
    string public symbol;      // 代币简写
    uint public tokenTotalSupply;       // 代币总量

    mapping(address => uint) private wallet;           // 喵凸币钱包

    constructor(string memory _name,string memory _symbol,address _admin,
        uint _tokenTotalSupply)public{
        creator = msg.sender;       // 创造者的地址
        admin = _admin;             // 管理员地址
        name = _name;               // NFT名称
        symbol = _symbol;           // 代币简写
        tokenTotalSupply = _tokenTotalSupply;       // 代币总量

        /*对该对象的钱包进行初始化，将代币总量数额的MTB放入管理员账户中*/
        wallet[admin] = tokenTotalSupply;
    }

    /*增加MTB的发行量*/
    function increaseCirculation(uint _num)public{
        require(msg.sender == creator,"Illegal calls");
        require(tokenTotalSupply + _num > tokenTotalSupply,"Out of memory(数值超额)！");
        require(wallet[admin] + _num > wallet[admin],"Out of memory(数值超额)！");

        tokenTotalSupply += _num;
        wallet[admin] += _num;
        emit IncreaseCirculation(_num);
    }

    /*减少MTB的发行量*/
    function reduceCirculation(uint _num)public{
        require(msg.sender == creator,"Illegal calls(错误调用)");
        require(tokenTotalSupply >= _num,"circulation can't be 0 or less(发行量不能为0或者负数)！");
        require(wallet[admin] >= _num,"admin lack of balance(管理员手中MTB余额不足)！ ");

        tokenTotalSupply -= _num;
        wallet[admin] -= _num;
        emit ReduceCirculation(_num);
    }


    /*获取某个用户的余额*/
    function getBalance(address _user)public view returns(uint balance){
        require(msg.sender == creator,"Illegal calls(错误调用)");
        return wallet[_user];
    }

    /*进行MTB的交易，若单独使用即为赠送*/
    function transaction(address _from,address _to,uint _num)public{
        require(msg.sender == creator,"Illegal calls(错误调用)");
        require(wallet[_from] >= _num,"_from lack of balance(余额不足)！");
        require(wallet[_to] + _num > wallet[_to],"Out of memory(数值超额)！");

        wallet[_from] -= _num;
        wallet[_to] += _num;
        emit Transaction(_from,_to,_num);
    }

    /*为某个用户充值MTB*/
    function topUpMTB(address _user,uint _num)public{
        require(msg.sender == creator,"Illegal calls(错误调用)");

        transaction(admin,_user,_num);
    }


}

/// NFT艺术品的接口，主要作用是用来存放事件
interface NFT{
    /* @dev 当NFT的任何所有权发送改变时，触发此事件 */
    event Transaction(address indexed _from, address indexed _to, uint indexed _tokenId,uint _share);
    /* @dev 当NFT被设置为商品时,触发此事件 */
    event AsGoods(address indexed _user, uint indexed _tokenId,uint _share);
    /* @dev 当NFT被取消为商品时,触发此事件 */
    event CancelAsGoods(address indexed _user, uint indexed _tokenId,uint _share);
}

/// 该智能合约为艺术品（NFT）的智能合约
/// 主管电子艺术品的相关方法和保存所有权等信息
contract Art is NFT{

    address public creator;        // 创造者
    address public admin;          // 管理员
    string public name;        // 币种名称
    string public symbol;      // 代币简写
    uint private MAX_SHARE = 100;       // 满额代币份额

    // token 艺术品
    struct Token{
        uint hash;
        address uploader;       // 上传者
        uint time;          // 上传时间
        bool isUsed;
    }

    // tokenId --> Token
    mapping(uint => Token) tokenMap;        // 所有艺术品的集合
    mapping(address => uint[]) ownerShip;       // 艺术品所有权
    mapping(uint => address[]) occupy;          // 被持有
    mapping(address => mapping(uint => uint)) shape;        // 所持有艺术品股份
    mapping(address => mapping(uint => uint)) goods;        // 待出售的艺术品股份

    constructor(string memory _name,string memory _symbol,address _admin)public{
        creator = msg.sender;       // 创造者的地址
        admin = _admin;             // 管理员地址
        name = _name;               // NFT名称
        symbol = _symbol;           // 代币简写

    }

    /*获取艺术品的信息*/
    function getUpload(uint _tokenId)public view returns(address uploader_,uint time_){
        require(msg.sender == creator,"Illegal calls(错误调用)");
        require(tokenMap[_tokenId].isUsed,"tokenId not exits(艺术品不存在)！");

        uploader_ = tokenMap[_tokenId].uploader;
        time_ = tokenMap[_tokenId].time;

    }

    /*上传一个艺术品*/
    function uploadNFT(uint _tokenId,uint _hash,address _uploader)public{
        require(msg.sender == creator,"Illegal calls(错误调用)");

        tokenMap[_tokenId].hash = _hash;
        tokenMap[_tokenId].uploader = _uploader;
        tokenMap[_tokenId].time = now;
        tokenMap[_tokenId].isUsed = true;

        ownerShip[_uploader].push(_tokenId);

        occupy[_tokenId].push(_uploader);

        shape[_uploader][_tokenId] = MAX_SHARE;
    }

    /*将艺术品的部分股份设置为待售状态*/
    function asGoods(address _user,uint _tokenId,uint _shape)public{
        require(msg.sender == creator,"Illegal calls(错误调用)");
        require(tokenMap[_tokenId].isUsed,"tokenId not exits(艺术品不存在)！");
        require(shape[_user][_tokenId] >= _shape,"shape not enough(现有份额不足)！");

        shape[_user][_tokenId] -= _shape;
        goods[_user][_tokenId] += _shape;
        emit AsGoods(_user,_tokenId,_shape);
    }

    /*将艺术品的部分股份取消待售状态*/
    function cancelAsGoods(address _user,uint _tokenId,uint _shape)public{
        require(msg.sender == creator,"Illegal calls(错误调用)");
        require(tokenMap[_tokenId].isUsed,"tokenId not exits(艺术品不存在)！");
        require(goods[_user][_tokenId] >= _shape,"shape not enough(现有份额不足)！");

        shape[_user][_tokenId] += _shape;
        goods[_user][_tokenId] -= _shape;
        emit CancelAsGoods(_user,_tokenId,_shape);
    }

    /*进度art的交易，若单独使用即为赠送*/
    function transaction(address _from,address _to,uint _tokenId,uint _shape)public{
        require(msg.sender == creator,"Illegal calls(错误调用)");
        require(tokenMap[_tokenId].isUsed,"tokenId not exits(艺术品不存在)！");
        require(goods[_from][_tokenId] >= _shape,"trade shape not enough(能够用来交易的份额不足)！");

        // 如果交易完该股份后，_from用户没有任何该_tokenId艺术品的股份，则去掉其在ownerShip与occupy中的位置
        if(shape[_from][_tokenId] == 0 && goods[_from][_tokenId] == _shape){
            for(uint i = 0;i < ownerShip[_from].length;i++){
                if(ownerShip[_from][i] == _tokenId){
                    ownerShip[_from][i] = ownerShip[_from][ownerShip[_from].length - 1];
                    delete ownerShip[_from][ownerShip[_from].length - 1];
                    ownerShip[_from].length -= 1;
                    break;
                }
            }
            for(uint j = 0;j < occupy[_tokenId].length;j++){
                if(occupy[_tokenId][j] == _from){
                    occupy[_tokenId][j] = occupy[_tokenId][occupy[_tokenId].length - 1];
                    delete occupy[_tokenId][occupy[_tokenId].length - 1];
                    occupy[_tokenId].length -= 1;
                    break;
                }
            }
        }
        goods[_from][_tokenId] -= _shape;
        emit CancelAsGoods(_from,_tokenId,_shape);
        // 如果在实施该交易之前，该用户没有任何该_tokenId艺术品的股份，则添加其在ownerShip与occupy中的位置
        if(shape[_to][_tokenId] == 0){
            ownerShip[_to].push(_tokenId);
            occupy[_tokenId].push(_to);
        }
        shape[_to][_tokenId] += _shape;
        emit Transaction(_from,_to,_tokenId,_shape);
    }

    /*查询一个用户拥有的艺术品*/
    function getArtsByAddress(address _user)public view returns(uint[] memory tokenIds){
        require(msg.sender == creator,"Illegal calls(错误调用)");

        uint[] storage tokens = ownerShip[_user];
        tokenIds = new uint[](tokens.length);
        for(uint i = 0;i < tokens.length;i++){
            tokenIds[i] = tokens[i];
        }
    }

    /*查询一个艺术品的拥有者*/
    function getAddressByTokenId(uint _tokenId)public view returns(address[] memory adds){
        require(msg.sender == creator,"Illegal calls(错误调用)");
        require(tokenMap[_tokenId].isUsed,"tokenId not exits(艺术品不存在)！");

        address[] storage addresses = occupy[_tokenId];
        adds = new address[](addresses.length);
        for(uint i = 0;i < addresses.length;i++){
            adds[i] = addresses[i];
        }
    }

    /*查询一个艺术品*/
    function getArtByTokenId(uint _tokenId)public view returns(uint hash_,address uploader_,uint time_,bool isUsed_){
        require(msg.sender == creator,"Illegal calls(错误调用)");
        require(tokenMap[_tokenId].isUsed,"tokenId not exits(艺术品不存在)！");
        hash_ = tokenMap[_tokenId].hash;
        uploader_ = tokenMap[_tokenId].uploader;
        time_ = tokenMap[_tokenId].time;
        isUsed_ = tokenMap[_tokenId].isUsed;
    }


}

contract Palette{
    address private creator;        // 创造者
    mapping(address => bool) users;     // 该合约的用户

    Art private art;
    MTB private mtb;

    constructor()public{
        creator = msg.sender;       // 创造者的地址
        users[creator] = true;      // 创造者也是一种用户

        /*合约的成员对象初始化，创造MTB和NFT*/
        art = new Art("Palette艺术品","NFT",msg.sender);
        mtb = new MTB("喵凸币","MTB",msg.sender,1e9);
    }

    /*获取艺术品的信息*/
    function getUpload(uint _tokenId)public view returns(address uploader_,uint time_){
        require(msg.sender == creator,"Insufficient permissions(只有创造者有权使用该方法)！");

        return art.getUpload(_tokenId);
    }


    /*为某个用户开户*/
    function setPower(address _user)public{
        require(msg.sender == creator,"Insufficient permissions(只有创造者有权使用该方法)！");
        require(!users[_user],"have been user(该地址已经是用户了)");
        users[_user] = true;
    }

    /*获取个地址是否为用户*/
    function getPower(address _user)public view returns(bool isUser){
        require(users[msg.sender],"Insufficient permissions(只有用户才有权查询)！");
        return users[_user];
    }

    /*获取对象art的相关信息*/
    function getArt()public view returns(address creator_,address admin_,string memory name_,string memory symbol_){
        require(users[msg.sender],"Insufficient permissions(只有用户才有权查询)！");
        creator_ = art.creator();
        admin_ = art.admin();
        name_ = art.name();
        symbol_ = art.symbol();
    }

    /*获取对象mtb的相关信息*/
    function getMTB()public view returns(address creator_,address admin_,
        string memory name_,string memory symbol_,uint tokenTotalSupply_){
        require(users[msg.sender],"Insufficient permissions(只有用户才有权查询)");
        creator_ = art.creator();
        admin_ = art.admin();
        name_ = art.name();
        symbol_ = art.symbol();
        tokenTotalSupply_ = mtb.tokenTotalSupply();
    }

    /*增加MTB的发行量*/
    function increaseCirculation(uint _num)public{
        require(msg.sender == creator,"Insufficient permissions(只有创建者才能使用该方法)！");

        mtb.increaseCirculation(_num);
    }

    /*减少MTB的发行量*/
    function reduceCirculation(uint _num)public{
        require(msg.sender == creator,"Insufficient permissions(只有创建者才能使用该方法)！");

        mtb.reduceCirculation(_num);
    }


    /*获取某个用户的余额*/
    function getBalance(address _user)public view returns(uint balance){
        require(users[msg.sender],"Insufficient permissions(只有用户才有权查询)！");
        require(users[_user],"user not exits(查询的用户不存在)!");

        return mtb.getBalance(_user);
    }

    /*进行MTB的交易,若单独使用即为赠送MTB*/
    function transactionMTB(address _to,uint _num)public{
        require(users[msg.sender],"Insufficient permissions(只有用户才有权使用该方法)！");
        require(users[_to],"_to must is user(_to必须是用户)！");
        require(_num != 0,"_num not 0(数额不能为0)!");

        mtb.transaction(msg.sender,_to,_num);
    }

    /*为某个用户充值MTB*/
    function topUpMTB(address _user,uint _num)public{
        require(msg.sender == creator,"Insufficient permissions(只有创建者才能使用该方法)！");
        require(users[_user],"user not exits(充值的目标用户不存在)!");
        require(_num != 0,"_num not 0(充值数不能为0)！");

        mtb.topUpMTB(_user,_num);
    }

    /*上传一个艺术品*/
    function uploadNFT(uint _tokenId,uint _hash)public{
        require(users[msg.sender],"Insufficient permissions(只有用户才有权使用该方法)！");

        art.uploadNFT(_tokenId,_hash,msg.sender);
    }

    /*将艺术品的部分股份设置为待售状态*/
    function asGoods(uint _tokenId,uint _shape)public{
        require(users[msg.sender],"Insufficient permissions(只有用户才有权使用该方法)！");
        require(_shape != 0,"_shape not 0(设置的股份不能为0)！");

        art.asGoods(msg.sender,_tokenId,_shape);
    }

    /*将艺术品的部分股份取消待售状态*/
    function cancelAsGoods(uint _tokenId,uint _shape)public{
        require(users[msg.sender],"Insufficient permissions(只有用户才有权使用该方法)！");
        require(_shape != 0,"_shape not 0(设置的股份不能为0)！");

        art.cancelAsGoods(msg.sender,_tokenId,_shape);
    }

    /*进度art的交易，若单独使用即为赠送NFT*/
    function transactionArt(address _to,uint _tokenId,uint _shape)public{
        require(users[msg.sender],"Insufficient permissions(只有用户才有权使用该方法)！");
        require(users[_to],"_to must is user(_to必须是用户)！");
        require(_shape != 0,"_shape not 0(设置的股份不能为0)！");

        art.transaction(msg.sender,_to,_tokenId,_shape);
    }

    /*查询一个用户拥有的艺术品*/
    function getArtsByAddress(address _user)public view returns(uint[] memory tokenIds){
        require(users[msg.sender],"Insufficient permissions(只有用户才有权使用该方法)！");
        require(users[_user],"_user must is user(_user必须是用户)！");

        return art.getArtsByAddress(_user);

    }

    /*查询一个艺术品的拥有者*/
    function getAddressByTokenId(uint _tokenId)public view returns(address[] memory adds){
        require(users[msg.sender],"Insufficient permissions(只有用户才有权使用该方法)！");

        return art.getAddressByTokenId(_tokenId);

    }

    /*交易，通过MTB对NFT进行交易*/
    function transactionArtAndMTB(address _to,uint _tokenId,uint _shape,uint _value)public{
        require(users[msg.sender],"Insufficient permissions(只有用户才有权使用该方法)！");
        require(users[_to],"_to must is user(_to必须是用户)！");
        require(_shape != 0,"_shape not 0(设置的股份不能为0)！");
        require(_value != 0,"_value not 0(充值数不能为0)！");

        mtb.transaction(msg.sender,_to,_value);
        art.transaction(msg.sender,_to,_tokenId,_shape);
    }

    /*查询一个艺术品*/
    function getArtByTokenId(uint _tokenId)public view returns(uint hash_,address uploader_,uint time_,bool isUsed_){
        require(users[msg.sender],"Insufficient permissions(只有用户才有权使用该方法)！");

        return art.getArtByTokenId(_tokenId);
    }


}

