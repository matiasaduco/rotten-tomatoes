import { useEffect, useState } from 'react'
import { useNavigate, useParams } from "react-router-dom"
import MovieCard from '../sub-components/MovieCard'
import Api from '../main/mainPage-components/Api'

const MovieCardContainer = ({request}) => {
  let { categoryId } = useParams()
  const navigateTo = useNavigate()
  const [movies, setMovies] = useState([])

  useEffect(() => {
    if(categoryId) {
      Api.getMoviesByCategory(categoryId).then((data) => setMovies(data.result)).catch(() => navigateTo('*'));
    } else {
      request().then((data) => setMovies(data.result)).catch(() => navigateTo('*'));
    }
  }, [categoryId])
  
    return(
      <div className='bg-light w-75 mx-auto'>
        <div className="container">
          <div className="row">
              {movies.map(dto => <MovieCard key={`MovieCardKey_${dto.id}`} dto={dto}/>)}
          </div>
        </div>
      </div>
    )
}

export default MovieCardContainer
