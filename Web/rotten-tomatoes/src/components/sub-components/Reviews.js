import ReviewDisplay from '../sub-components/ReviewDisplay'
import Pagination from './Pagination'

const Reviews = ({simpleMovie, activePage, setActivePage, reviews, reviewPerPage, user}) => {

    return(
            <div className='row'>
                <div className="content-tabs">
                   {reviews.map((review, index) => <ReviewDisplay key={`ReviewDisplayKey_${review.id}`} reviewDTO={review} activePage={activePage} index={index} reviewPerPage={reviewPerPage} user={user} />)}
                </div>
                <Pagination key={`PaginationKey_${simpleMovie.id}`} activePage={activePage} setActivePage={setActivePage} list={reviews} componentPerPage={reviewPerPage} />
            </div>
    )
}

export default Reviews
