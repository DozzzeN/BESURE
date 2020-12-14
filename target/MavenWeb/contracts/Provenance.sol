pragma solidity ^0.4.24;

contract Provenance {
    // 溯源记录结构
    struct Prov {
        uint256 idP; // 所有者
        uint256 stageNumber; // 溯源记录阶段编号
        address operator; // 操作者
        string content; // 溯源记录内容
    }

    mapping(uint256 => mapping(uint256 => Prov)) public proves; // 所有溯源记录（ID-阶段编号-文件结构）
    mapping(uint256 => uint256) public index; // idP的记录个数

    function Create(
        uint256 _idP,
        string memory _content
    ) public {
        Prov memory _prov = Prov({
        idP : _idP,
        stageNumber : index[_idP],
        operator : msg.sender,
        content : _content
        });
        proves[_idP][index[_idP]] = _prov;
        index[_idP]++;
    }

    function getProv(
        uint256 _idP
    ) public view
    returns (string _content) {
        _content = proves[_idP][index[_idP] - 1].content;
    }
}