import tomato from '../../images/tomato.png'
import { useNavigate } from 'react-router-dom'

const RelatedContentDisplay = ({relatedContentDTO, activePage, index, reviewPerPage}) => {
    const navigate = useNavigate()
    const navigateToMoviePage = () => navigate(`/content/${relatedContentDTO.id}`, {state: relatedContentDTO})

    return(
            <div className={`d-flex mb-3 border border-2 ${Math.ceil((index + 1) / reviewPerPage) === activePage ? 'd-block' : 'd-none'}`}>
                <button className='border-0 bg-transparent p-0 w-25' onClick={navigateToMoviePage}><img src={relatedContentDTO.poster} className="card-img-top" /></button>
                <div className='container'>
                    <div className='row border border-top-0 border-start-0 border-end-0 border-dark'>
                        <div className='col-md-auto'>
                            <h3 className='mt-1 ms-3'>{relatedContentDTO.title}</h3>
                        </div>
                        <div className='col position-relative d-flex align-items-end flex-column'>
                            <span className='d-flex ms-5 text-secondary opacity-75'>
                                <img src={tomato} width="40" height="34" className="d-inline-block align-text-top mt-1" />
                                <h3 className='mt-1'>{relatedContentDTO.score}/5</h3>
                            </span>
                        </div>
                    </div>
                    <div className='row'>
                        <div className='col'>
                            {relatedContentDTO.description}
                        </div>
                    </div>
                    <div className="position-relative d-flex align-items-end flex-column">
                        <div className="mt-auto p-2"><button type="button" className="btn btn-primary">Open</button></div>
                    </div>
                </div>
            </div>
    )
}

export default RelatedContentDisplay
