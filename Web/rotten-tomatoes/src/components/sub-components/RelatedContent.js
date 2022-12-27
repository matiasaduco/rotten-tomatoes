import { useEffect, useState } from "react"
import Api from "../main/mainPage-components/Api"
import RelatedContentDisplay from "./RelatedContentDisplay"
import Pagination from './Pagination'

const RelatedContent = ({simpleMovie, activePage, setActivePage, relatedContentsPerPage}) => {
    const [relatedContents, setRelatedContent] = useState([])

    useEffect(() => {
        simpleMovie && Api.getMovieByID(simpleMovie.id).then((data) => setRelatedContent(data.relatedContent))
    }, [])

    return(
            <div className='row'>
                <div className="content-tabs">
                    {relatedContents.map((relatedContent, index) => <RelatedContentDisplay key={`RelatedContentKey_${relatedContent.id}`} relatedContentDTO={relatedContent} activePage={activePage} index={index} reviewPerPage={relatedContentsPerPage}/>)}
                </div>
                <Pagination activePage={activePage} setActivePage={setActivePage} list={relatedContents} componentPerPage={relatedContentsPerPage} />
            </div>
    )
}

export default RelatedContent
