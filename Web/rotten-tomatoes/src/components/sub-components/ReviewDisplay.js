import { useNavigate } from 'react-router-dom'
import tomato from '../../images/tomato.png'

const ReviewDisplay = ({reviewDTO, activePage, index, reviewPerPage, user}) => {
    const navigate = useNavigate()
    const navigateToProfilePage = () => navigate(`/profile/${reviewDTO.user.id}`)

    return(
            <div className={`modal-body p-3 ${Math.ceil((index + 1) / reviewPerPage) === activePage ? 'd-block' : 'd-none'}`} id="reviews">
                <div className={`row mb-3 ${!!user && reviewDTO.user.id === user.id ? 'border border-warning border-2' : 'border border-dark'}`}>
                    <div className='row'>
                        <div className='col'>
                            <button className='d-flex ms-2 mt-2 border-0 bg-transparent p-0' onClick={navigateToProfilePage}>
                                <h3 className={`${!!user && reviewDTO.user.id === user.id ? 'text-warning' : ''}`}>{reviewDTO.user.name}</h3>
                                <img src={reviewDTO.user.image} alt="User avatar" width='36' height='30'/>
                                {!!user && reviewDTO.user.id === user.id ? '(You)' : ''}
                            </button>
                        </div>
                        <div className='col'>
                            <span className='d-flex ms-5 mt-2 text-secondary opacity-75'>
                                <img src={tomato} alt="Tomato icon" width="30" height="24" className="d-inline-block align-text-top" />
                                <h6 className='mt-1'>{reviewDTO.stars}/5</h6>
                            </span>
                        </div>
                    </div>
                    <hr></hr>
                    <div>{reviewDTO.text}</div>
                </div>
            </div>
    )
}

export default ReviewDisplay
