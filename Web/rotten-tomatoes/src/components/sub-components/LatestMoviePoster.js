const LatestMoviePoster = ({poster, condition}) => {
    const state = condition ? ' active' : ''

    return(
        <div className={`carousel-item${state}`}>
            <img src={poster} alt="Movie poster" className="d-block w-25 mx-auto" />
        </div>
    )
}

export default LatestMoviePoster
