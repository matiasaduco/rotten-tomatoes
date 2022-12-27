/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import tomato from '../../../images/tomato.png'
import GenericTab from './GenericTab'
import TabContent from './TabContent'
import RelatedContent from '../../sub-components/RelatedContent'
import Reviews from '../../sub-components/Reviews'
import review from './images/reviews.ico'
import addreview from './images/addreview.ico'
import synopsis from './images/synopsis.ico'
import relatedcontent from './images/related-content.ico'
import AddReview from './AddReview'
import Api from '../mainPage-components/Api'

const MoviePage = () => {
    let { id } = useParams()
    const navigateTo = useNavigate()

    const nameOfFirstTab = "Synopsis"
    const nameOfSecondTab = "Reviews"
    const nameOfThirdTab = "Related-Content"
    const nameOfFourthTab = "Add-Review"

    const [getActiveNavTab, setActiveNavTab] = useState(nameOfFirstTab)
    const [activePage, setActivePage] = useState(1)
    const componentPerPage = 2

    const [user, setUser] = useState()
    const [movie, setMovie] = useState()
    const [reviews, setReviews] = useState([])

    useEffect(() => { Api.getMovieByID(id).then((data) => setMovie(data)).catch(() => navigateTo('/404')) },[])

    useEffect(() => {
        const isThereATokenStored = () => { return !!localStorage.getItem('Token') }
        if(!user && isThereATokenStored()) { Api.getUser(localStorage.getItem('Token')).then(data => setUser(data));  }
        Api.getMovieByID(id).then((data) => setReviews(ordenar(data.reviews)))
    }, [reviews])

    const ordenar = (lista) => {
        const checkIfUserMadeReview = (review) => { return review.user.id === user.id }
        const result = (!!user) ? lista.filter(elemento => checkIfUserMadeReview(elemento)).concat(lista.filter(elemento => !checkIfUserMadeReview(elemento))) : lista
        return result
    }


    if(!!movie) {
        return(
                <div className='d-flex bg-light w-75 mx-auto'>
                    <img src={movie.poster} alt="Movie poster"></img>
                    <div className='container'>
                        <div className='row border border-top-0 border-start-0 border-end-0 border-dark'>
                            <div className='col-md-auto'>
                                <h3 className='mt-1 ms-3'>{movie.title}</h3>
                            </div>
                            <div className='col position-relative d-flex align-items-end flex-column'>
                                <span className='d-flex ms-5 text-secondary opacity-75'>
                                    <img src={tomato} alt="Tomato icon" width="40" height="34" className="d-inline-block align-text-top mt-1" />
                                    <h3 className='mt-1'>{movie.score}/5</h3>
                                </span>
                            </div>
                        </div>
                        <div className='row'>
                            <ul className="nav nav-tabs">
                                <GenericTab name={nameOfFirstTab}  icon={synopsis}       getActiveNavTab={getActiveNavTab} setActiveNavTab={setActiveNavTab} />
                                <GenericTab name={nameOfSecondTab} icon={review}         getActiveNavTab={getActiveNavTab} setActiveNavTab={setActiveNavTab} />
                                <GenericTab name={nameOfThirdTab}  icon={relatedcontent} getActiveNavTab={getActiveNavTab} setActiveNavTab={setActiveNavTab} />
                                <GenericTab name={nameOfFourthTab} icon={addreview}      getActiveNavTab={getActiveNavTab} setActiveNavTab={setActiveNavTab} />
                            </ul>
                             <div className="content-tabs">
                                <TabContent name={nameOfFirstTab}  content={movie.description} getActiveTab={getActiveNavTab} />
                                
                                <TabContent name={nameOfSecondTab} content={<Reviews key={`ReviewsKey_${id}`} simpleMovie={movie} activePage={activePage} setActivePage={setActivePage} 
                                                                                       reviews={reviews} reviewPerPage={componentPerPage} user={user}/>} 
                                                                                       getActiveTab={getActiveNavTab} />
                                
                                <TabContent name={nameOfThirdTab}  content={<RelatedContent key={`RelatedContentKey_${id}`} simpleMovie={movie} activePage={activePage} 
                                                                                             setActivePage={setActivePage} relatedContentsPerPage={componentPerPage}/>} 
                                                                                             getActiveTab={getActiveNavTab} />

                                <TabContent name={nameOfFourthTab} content={<AddReview movie={id} setReviews={setReviews}/>} getActiveTab={getActiveNavTab} />
                            </div>
                        </div>
            
                    </div>
                </div>
        )
    }
}

export default MoviePage
