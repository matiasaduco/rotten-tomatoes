import Api from './Api'
import MovieCardContainer from "../../sub-components/MovieCardContainer";

const TopMovies = () => {
    return (
        <div className='bg-light w-75 mx-auto'>
            <MovieCardContainer request={Api.getTopMovies} name='Top Movies'/>
        </div>
    );
}

export default TopMovies
