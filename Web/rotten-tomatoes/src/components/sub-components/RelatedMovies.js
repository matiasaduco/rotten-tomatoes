import {useState } from "react"
import RelatedContentDisplay from "./RelatedContentDisplay"
import Pagination from './Pagination'

const RelatedMovies = ({movies}) => {
    const [relatedContents, setRelatedContent] = useState(movies)
    const [activePage, setActivePage] = useState(1)
    const relatedContentsPerPage = 2
    
    return(
            <div className='row'>
                <div className="content-tabs">
                    {relatedContents.map((movie, index) => <RelatedContentDisplay key={`MovieByReviewKey_${movie.id}`} relatedContentDTO={movie} activePage={activePage} index={index} reviewPerPage={relatedContentsPerPage}/>)}
                </div>
                <Pagination activePage={activePage} setActivePage={setActivePage} list={movies} componentPerPage={relatedContentsPerPage} />
            </div>
    )
}
export default RelatedMovies
