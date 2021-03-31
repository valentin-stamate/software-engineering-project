import SignUpUtility from './SignUpUtility';
import ClientInfo from './ClientInfo';

class ClientSignUp{
    static signup(info){
        SignUpUtility.signupClient(info);
    }
}

export default ClientSignUp;