import { useNavigate } from 'react-router-dom'
import tomato from '../../images/tomato.png'

const MovieCard = ({dto}) => {
    const description = String(dto.description)
    const reducedDescription = description.length > 100 ? description.substring(0, 100) + '...' : description
    const navigate = useNavigate()
    const navigateToMoviePage = () => navigate(`/content/${dto.id}`, {state: dto})

    return(
            <div className="col-md-3 mb-3 mt-2">
                <div className="card">
                    <button className='border-0 bg-transparent p-0' onClick={navigateToMoviePage}><img src={dto.poster} className="card-img-top"/></button>
                    <div className="card-body">
                        <div className='d-flex'>
                            <h4 className="card-title">{dto.title}</h4>
                            <span className='d-flex ms-3 mt-1 text-secondary opacity-75'>
                                <img src={tomato} alt="Tomato icon" width="30" height="24" className="d-inline-block align-text-top" />
                                <strong>{dto.score}/5</strong>
                            </span>
                        </div>
                        <p className="card-text">{reducedDescription}</p>
                        <button type="button" className="btn btn-primary" onClick={navigateToMoviePage}>Open</button>
                    </div>
                </div>
            </div>
    )
}

export default MovieCard
