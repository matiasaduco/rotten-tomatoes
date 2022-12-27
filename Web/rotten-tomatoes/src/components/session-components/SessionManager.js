/* eslint-disable react-hooks/exhaustive-deps */
import { useState, useEffect, Fragment} from 'react'
import Api from "../main/mainPage-components/Api"
import InvitedSessionView from './InvitedSessionView'
import LogedView from './LogedView'
import ToastMessage from './ToastMessage'

const SessionManager = ({setAnActiveSession}) => {
    const [getLoginData, setLoginData] = useState()
    const [getRegisterData,setRegisterData] = useState()
    const [getShowFlag, setShowFlag] = useState()
    const [getMessage, setMessage] = useState('That you enjoy our page :)')
    const defaultView = <InvitedSessionView setLoginData={setLoginData} setRegisterData={setRegisterData}/>
    const defaultToastMessage = <ToastMessage getMessage={getMessage} getShowFlag={getShowFlag} setShowFlag={setShowFlag}/>
    const [actualView, setActualView] = useState(defaultView)

    useEffect(() => {
        const isThereDataToLogin = () => { return !!getLoginData }
        const isThereATokenStored = () => { return !!localStorage.getItem('Token') }

        const loginAUserByPanel = () => {
            Api.postLogIn(getLoginData.mail, getLoginData.pw)
            .then((response) => { setActualView(<LogedView data={response.data} setLogOutView={setLogOutView}/>); setMessage(`Welcome: ${response.data.name}`); localStorage.setItem('Token', response.headers.authorization)})
            .catch(({response}) => setMessage(response.data.message))
        }
    
        const loginAUserToken = () => {
            const userToken = localStorage.getItem('Token')
            Api.getUser(userToken)
            .then((response) => { setActualView(<LogedView data={response} setLogOutView={setLogOutView}/>); setMessage(`Welcome: ${response.name}`)})
            .catch(({response}) => setMessage(response.data.message))
        }

        const setLogOutView = () => { setActualView(defaultView); setMessage(`Come back soon!!`); localStorage.removeItem('Token'); setAnActiveSession(false)}


        if(isThereDataToLogin()) {  
            loginAUserByPanel()  
            setAnActiveSession(true)      
        } else if(isThereATokenStored()) {
            loginAUserToken()
            setAnActiveSession(true)
        }

        setLoginData()

    }, [getLoginData])

    useEffect(() => {
        const registerUser = () => {
            Api.postRegister(getRegisterData.mail, getRegisterData.pw, getRegisterData.image, getRegisterData.name)
            .then(() => setMessage(`Welcome to RT, your account was registered`))
            .catch(({response}) => setMessage(`Error: ${response.data.message}`))
        }
        
        if(!!getRegisterData) {
            registerUser()
        }

        setRegisterData()

    }, [getRegisterData])

    useEffect(() => {
        setShowFlag('show')
    }, [getMessage])


    return (
        <Fragment>
            {actualView}
            {defaultToastMessage}
        </Fragment>
        )
}

export default SessionManager
