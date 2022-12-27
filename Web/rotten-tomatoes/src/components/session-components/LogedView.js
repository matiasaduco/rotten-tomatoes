import { Fragment } from "react"
import { useNavigate } from "react-router-dom"

const LogedView = ({data, setLogOutView}) => { 
    const navigateTo = useNavigate()

    return(
        <Fragment>            
            <button className="btn" type="button" onClick={() => navigateTo(`/profile/${data.id}`)}><img src={data.image} width='20' height='20'/> {data.name.toUpperCase()}</button>
            <button className="btn" type="button" onClick={ setLogOutView }>Log-Out</button>       
        </Fragment>
    )                
}

export default LogedView
