import Api from "../mainPage-components/Api"
import { useEffect, useState } from "react"
import ToastMessage from "../../session-components/ToastMessage"

const AddReview = ({movie, setReviews}) => {
    const [textReview, setTextReview ]= useState('')
    const [stars, setStart ]= useState(1)
    const [getShowFlag, setShowFlag] = useState()
    const [getMessage, setMessage] = useState()
    const defaultToastMessage = <ToastMessage getMessage={getMessage} getShowFlag={getShowFlag} setShowFlag={setShowFlag}/>

    const onClickHand = () => {
        Api.postReview(movie, textReview, stars, localStorage.getItem('Token'))
        .then((response) => { setMessage('The review was registered successfully'); setReviews((prevState => ([response.data, ...prevState])))})
        .catch(({response}) => setMessage(`Error: ${response.data.message}`))
        setTextReview('')
        setStart(1)
    }

    useEffect(() => {
        if(!!getMessage) {
            setShowFlag('show')
        } else {
            setShowFlag('hide')
        }
    },[getMessage])

    return (
        <div className="mb-3">
            <div className="form-floating mb-3">
                <input type="email" value={textReview} onChange={(event) => setTextReview(event.target.value)} className="form-control" maxLength="100" id="floatingTextarea" placeholder="Leave a comment here"/>
                <label htmlFor="floatingInput">Enter your comment...</label>
            </div>   
            <p>Select a number of stars</p>        
            <select className="form-select" value={stars} onChange={(event) => setStart(event.target.value)} id="starForm" aria-label="Default select example">
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
                <option value="4">Four</option>
                <option value="5">Five</option>
            </select>

            <button disabled={!localStorage.getItem('Token')} className="btn btn-outline-success mt-3" type="button" onClick={ () => onClickHand() } >Send</button>
            {defaultToastMessage}
        </div>
    )
}

export default AddReview