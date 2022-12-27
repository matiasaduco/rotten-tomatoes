import { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import GenericTab from './GenericTab'
import Api from "../mainPage-components/Api"
import RelatedMovies from "../../sub-components/RelatedMovies";
import relatedcontent from "../../main/internalPages-components/images/related-content.ico";

const Profile = () => {
    let { id } = useParams()
    const [userData, setUserData] = useState([])
    const [movies, setMovies] = useState([])
    const nameTab = "Movies By Review"
    
    useEffect(() => {
        Api.getUserById(id).then(data => {setUserData(data); setMovies(data.reviews.map(review => review.movie))})
    }, [id])

    return(
        <div key={"profile_content_" + {id}} className='d-flex bg-light w-75 mx-auto'>
            <img src={userData.image} alt="User avatar" width='500' height='500'></img>
            <div className='container'>
                <h1 className='ms-5'>{userData.name}</h1>
                <div key={"row" + {id}} className='row'>
                    <ul className="nav nav-tabs">
                        <GenericTab name={nameTab}  icon={relatedcontent} getActiveNavTab={nameTab}/>
                    </ul>
                    <div key={"content_" + {id}} className="content-tabs">
                        <RelatedMovies key={`RelatedMoviesKey_${userData.id}`} movies={movies} />
                    </div>
                </div>
            </div>
        </div>
    )
}



export default Profile
