import { Fragment, useEffect, useState } from 'react'
import MovieCard from '../sub-components/MovieCard'
import Api from '../main/mainPage-components/Api'


const TopMovieCardContainer = ({category}) => {
    const [movies, setMovies] = useState([])
    
    useEffect(() => {
      Api.getMoviesByCategory(category.id).then(({result}) => setMovies(result))
    }, [])

    return(
      <Fragment>
        {movies.length &&
        <span>
          <hr className='ms-5 me-5 mx-auto'/>
          <div className="container">
            <h3><span className="badge bg-secondary">{`Best ${category.name} Movies`}</span></h3>
            <div className="row">
                {movies.slice(0, 4).sort((a, b) => {return b.score - a.score}).map(dto => <MovieCard key={`MovieCardKey_${dto.id}`} dto={dto}/>)}
            </div>
          </div>
        </span>}
      </Fragment>
    )
}

export default TopMovieCardContainer
