package contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.5.5.
 */
@SuppressWarnings("rawtypes")
public class Provenance_sol_Provenance extends Contract {
    public static final String FUNC_CREATE = "Create";
    public static final String FUNC_GETPROV = "getProv";
    public static final String FUNC_INDEX = "index";
    public static final String FUNC_PROVES = "proves";
    public static final Event CONTENT_EVENT = new Event("Content",
            Arrays.asList(new TypeReference<Utf8String>() {
            }));
    private static final String BINARY = "608060405234801561001057600080fd5b5061064d806100206000396000f3fe608060405234801561001057600080fd5b506004361061004c5760003560e01c80631575573814610051578063335932fc1461007d578063ac41c7d51461009d578063bba37318146100bd575b600080fd5b61006461005f3660046104b8565b6100d2565b6040516100749493929190610547565b60405180910390f35b61009061008b3660046103f3565b610197565b604051610074919061053e565b6100b06100ab3660046103f3565b6101a9565b6040516100749190610524565b6100d06100cb36600461040b565b6102a8565b005b60006020818152928152604080822090935290815220805460018201546002830154600384018054939492936001600160a01b03909216929161011490610595565b80601f016020809104026020016040519081016040528092919081815260200182805461014090610595565b801561018d5780601f106101625761010080835404028352916020019161018d565b820191906000526020600020905b81548152906001019060200180831161017057829003601f168201915b5050505050905084565b60016020526000908152604090205481565b6000818152602081815260408083206001928390529083205460609391926101d09161057e565b815260200190815260200160002060030180546101ec90610595565b80601f016020809104026020016040519081016040528092919081815260200182805461021890610595565b80156102655780601f1061023a57610100808354040283529160200191610265565b820191906000526020600020905b81548152906001019060200180831161024857829003601f168201915b505050505090507f613d9cd2bbf3e47bb693c60a9c4c7c21be4584814d8889e845f53b8874d7a8a68160405161029b9190610524565b60405180910390a1919050565b604080516080810182528381526000848152600160208181528483205481850181815233868801908152606087018981528a8752868552888720938752928452969094208551815593519284019290925593516002830180546001600160a01b0319166001600160a01b0390921691909117905551805192938493610333926003850192019061035a565b5050506000838152600160205260408120805491610350836105d0565b9190505550505050565b82805461036690610595565b90600052602060002090601f01602090048101928261038857600085556103ce565b82601f106103a157805160ff19168380011785556103ce565b828001600101855582156103ce579182015b828111156103ce5782518255916020019190600101906103b3565b506103da9291506103de565b5090565b5b808211156103da57600081556001016103df565b600060208284031215610404578081fd5b5035919050565b6000806040838503121561041d578081fd5b8235915060208084013567ffffffffffffffff8082111561043c578384fd5b818601915086601f83011261044f578384fd5b81358181111561046157610461610601565b604051601f8201601f191681018501838111828210171561048457610484610601565b604052818152838201850189101561049a578586fd5b81858501868301378585838301015280955050505050509250929050565b600080604083850312156104ca578182fd5b50508035926020909101359150565b60008151808452815b818110156104fe576020818501810151868301820152016104e2565b8181111561050f5782602083870101525b50601f01601f19169290920160200192915050565b60006020825261053760208301846104d9565b9392505050565b90815260200190565b600085825284602083015260018060a01b03841660408301526080606083015261057460808301846104d9565b9695505050505050565b600082821015610590576105906105eb565b500390565b6002810460018216806105a957607f821691505b602082108114156105ca57634e487b7160e01b600052602260045260246000fd5b50919050565b60006000198214156105e4576105e46105eb565b5060010190565b634e487b7160e01b600052601160045260246000fd5b634e487b7160e01b600052604160045260246000fdfea26469706673582212205663a6ef7ce1e5aa268ac679247185d1191522cf3a05f1251e94393951d284f764736f6c63430008000033";

    @Deprecated
    protected Provenance_sol_Provenance(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Provenance_sol_Provenance(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Provenance_sol_Provenance(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Provenance_sol_Provenance(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    @Deprecated
    public static Provenance_sol_Provenance load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Provenance_sol_Provenance(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Provenance_sol_Provenance load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Provenance_sol_Provenance(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Provenance_sol_Provenance load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Provenance_sol_Provenance(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Provenance_sol_Provenance load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Provenance_sol_Provenance(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Provenance_sol_Provenance> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Provenance_sol_Provenance.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Provenance_sol_Provenance> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Provenance_sol_Provenance.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Provenance_sol_Provenance> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Provenance_sol_Provenance.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Provenance_sol_Provenance> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Provenance_sol_Provenance.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<ContentEventResponse> getContentEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CONTENT_EVENT, transactionReceipt);
        ArrayList<ContentEventResponse> responses = new ArrayList<ContentEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ContentEventResponse typedResponse = new ContentEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._content = (String) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ContentEventResponse> contentEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, ContentEventResponse>() {
            @Override
            public ContentEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CONTENT_EVENT, log);
                ContentEventResponse typedResponse = new ContentEventResponse();
                typedResponse.log = log;
                typedResponse._content = (String) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ContentEventResponse> contentEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CONTENT_EVENT));
        return contentEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> Create(BigInteger _idP, String _content) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATE,
                Arrays.asList(new org.web3j.abi.datatypes.generated.Uint256(_idP),
                        new org.web3j.abi.datatypes.Utf8String(_content)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> getProv(BigInteger _idP) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_GETPROV,
                Arrays.asList(new org.web3j.abi.datatypes.generated.Uint256(_idP)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> index(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_INDEX,
                Arrays.asList(new org.web3j.abi.datatypes.generated.Uint256(param0)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> proves(BigInteger param0, BigInteger param1) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PROVES,
                Arrays.asList(new org.web3j.abi.datatypes.generated.Uint256(param0),
                        new org.web3j.abi.datatypes.generated.Uint256(param1)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public static class ContentEventResponse extends BaseEventResponse {
        public String _content;
    }
}
