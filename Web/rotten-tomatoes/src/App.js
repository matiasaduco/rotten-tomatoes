import ProfilePage from './components/main/internalPages-components/ProfilePage'
import MovieCardContainer from './components/sub-components/MovieCardContainer'
import MoviePage from './components/main/internalPages-components/MoviePage'
import LatestMovies from './components/main/mainPage-components/LatestMovies'
import TopMovies from './components/main/mainPage-components/TopMovies'
import NotFound from './components/main/mainPage-components/NotFound'
import NavBar from './components/main/mainPage-components/NavBar'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Home from './components/main/mainPage-components/Home'
import image from './images/Solid_black.png'
import './App.css'
import { useEffect, useState } from 'react'

function App() { 
  const [isThereAnActiveSession, setAnActiveSession] = useState(false)

  useEffect(() => {}, [isThereAnActiveSession])

  return (
      <div style={{backgroundImage:`url(${image})`}}>
        <BrowserRouter>
          <Routes>
            <Route path='/' element={<NavBar setAnActiveSession={setAnActiveSession}/>}>
              <Route index element={<Home />}/>
              <Route path='/content'>
                <Route path='top_movies' element={<TopMovies />} />
                <Route path='latest_movies' element={<LatestMovies />} />
                <Route path=':id' element={<MoviePage key={`MoviePage_${isThereAnActiveSession}`}/>} />
              </Route>
              <Route path='/profile'>
                <Route path=':id' element={<ProfilePage />} />
              </Route>
              <Route path='/categories'>
                <Route path=':categoryId' element={<MovieCardContainer />}/>
              </Route>
              <Route path='*' element={<NotFound />}/>
            </Route>
          </Routes>
        </BrowserRouter>
      </div>
  );
}

export default App;
