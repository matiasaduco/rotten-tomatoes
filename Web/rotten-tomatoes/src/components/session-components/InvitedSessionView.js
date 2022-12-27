import { useRef } from "react"

const InvitedSessionView = ({setLoginData, setRegisterData}) => { 
    const mailInputLogIn = useRef()
    const pwInputLogIn = useRef()
    const mailInputRegister = useRef()
    const pwInputRegister = useRef()
    const imageInputRegister = useRef()
    const nameInputRegister = useRef()

    return( 
    <div className="btn-group dropstart me-3">
        <div>
            <button className="btn dropdown-toggle" id="loginBtn" data-bs-toggle="dropdown" aria-expanded="false">
                LogIn
            </button>
            <ul className="dropdown-menu" aria-labelledby="loginBtn">
                <h5 className="float-center mt-3 mb-3" style={{textAlign: `center`}}>MY ACCOUNT</h5>
                <form className="p-1">
                    <input className="form-control mb-2" ref={mailInputLogIn} type="text"     id="mailInuputLogIn"    placeholder="Mail address"/>
                    <input className="form-control mb-4" ref={pwInputLogIn}   type="password" id="passwordInputLogIn" placeholder="Password"/>
                </form>
                <li><button className="btn btn-secondary w-100 rounded-0 mb-1" onClick={ () => { setLoginData({mail:mailInputLogIn.current.value, pw:pwInputLogIn.current.value}) }}>Log-In</button></li>
            </ul>
        </div>
        <div>
            <button className="btn dropdown-toggle" id="registerBtn" data-bs-toggle="dropdown" aria-expanded="false">Register</button>
            <ul className="dropdown-menu" aria-labelledby="registerBtn">
                <h5 className="float-center mt-3 mb-3" style={{textAlign: `center`}}>REGISTER</h5>
                <form className="p-1">
                    <input className="form-control mb-2" ref={mailInputRegister}  type="text"     id="mailInuputRegister"  placeholder="Mail address"/>
                    <input className="form-control mb-2" ref={pwInputRegister}    type="password" id="pwInputRegister"     placeholder="Password"/>
                    <input className="form-control mb-2" ref={imageInputRegister} type="text"     id="imageInuputRegister" placeholder="Image URL"/>
                    <input className="form-control mb-2" ref={nameInputRegister}  type="text"     id="nameInuputRegister"  placeholder="Name"/>
                </form>
                <li><button className="btn btn-secondary w-100 rounded-0 mb-1" onClick={ () => setRegisterData({mail:mailInputRegister.current.value, pw:pwInputRegister.current.value, image:imageInputRegister.current.value, name:nameInputRegister.current.value})}>Register</button></li>
            </ul>
        </div>
    </div>
    )
}

export default InvitedSessionView
