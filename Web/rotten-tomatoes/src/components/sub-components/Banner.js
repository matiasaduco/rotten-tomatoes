import LatestMoviePoster from "./LatestMoviePoster";
import { useEffect, useState } from 'react'
import Api from '../main/mainPage-components/Api'
import { useNavigate } from "react-router-dom";

const Banner = () => {
  const [latestMovies, setLatestMovies] = useState([])
  const navigateTo = useNavigate()
    
  useEffect(() => {
    Api.getLatestMovies().then((data) => setLatestMovies(data.result)).catch(() => navigateTo('*'));
  }, [])

    return(
        <div id="carouselExampleIndicators" className="carousel slide bg-dark bg-gradient" data-bs-ride="true">
          <div className="carousel-indicators">
              <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
              <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
              <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
          </div>
          <div className="carousel-inner">
            {latestMovies.slice(0, 3).map((movie, index) => <LatestMoviePoster  key={`LatestMoviePosterKey_${movie.id}`} poster={movie.poster} condition={index === 0} />)}
          </div>
          <button className="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
              <span className="carousel-control-prev-icon" aria-hidden="true"></span>
              <span className="visually-hidden">Previous</span>
          </button>
          <button className="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
              <span className="carousel-control-next-icon" aria-hidden="true"></span>
              <span className="visually-hidden">Next</span>
          </button>
        </div>
    );
}

export default Banner
