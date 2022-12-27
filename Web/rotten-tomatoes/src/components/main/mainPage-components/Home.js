import { Fragment, useEffect, useState } from "react"
import { useNavigate } from "react-router-dom"
import Banner from "../../sub-components/Banner"
import TopMovieCardContainer from "../../sub-components/TopMovieCardContainer"
import Api from './Api'

const Home = () => {
  const [categories, setCategories] = useState([])
  const navigateTo = useNavigate()
  
  useEffect(() => {
    Api.getCategories().then((data) => setCategories(data.result)).catch(() => navigateTo('404'));
  }, [])

  return(
      <div className="bg-light w-75 mx-auto">
        <Banner />
        {categories.map(category => <TopMovieCardContainer key={`TopMovieCardContainerKey_${category.id}`} category={category}/>)}
      </div>
  )
}

export default Home;
