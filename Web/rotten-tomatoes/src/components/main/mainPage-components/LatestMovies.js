import Api from './Api'
import MovieCardContainer from "../../sub-components/MovieCardContainer"

const LatestMovies = () => {
    return (
        <div className='bg-light w-75 mx-auto'>
            <MovieCardContainer request={Api.getLatestMovies} name='Latest Movies'/>
        </div>
    );
}

export default LatestMovies
