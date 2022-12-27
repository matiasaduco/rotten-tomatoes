import Axios from 'axios'

const get = (url,config) => Axios.get(url,config).then(({data}) => data)
const post = (url,body,config) => Axios.post(url,body,config).then(response => response)
const apiURL = "http://localhost:7080"

const getCategories = () => get(`${apiURL}/categories`, {})
const getTopMovies = () => get(`${apiURL}/content/top`, {})
const getLatestMovies = () => get(`${apiURL}/content/latest`, {})
const getMovieByID = (id) => get(`${apiURL}/content/${id}`, {})
const getMoviesByCategory = (id) => get(`${apiURL}/categories/${id}`)
const getUser = (token) => get(`${apiURL}/user`, { headers: { 'Authorization': `${token}`}})
const getUserById = (id) => get(`${apiURL}/user/${id}`, {})

const postLogIn = (emailToLogin, passwordToLogin) => post(`${apiURL}/login`, { "email": `${emailToLogin}`, "password": `${passwordToLogin}` })
const postReview = (id, text, stars, token) => post(`${apiURL}/content/${id}`, { "text": `${text}`, "stars": `${stars}` }, { headers: { 'Authorization': `${token}`}})
const postRegister = (emailToRegister, passwordToRegister, imageLink, nameToRegister) => post(`${apiURL}/register`, { "email": `${emailToRegister}`, 
                                                                                                                      "password": `${passwordToRegister}`, 
                                                                                                                      "image": `${imageLink}`, 
                                                                                                                      "name": `${nameToRegister}` })

const Api = {
  getCategories,
  getTopMovies,
  getLatestMovies,
  getMovieByID,
  getMoviesByCategory,
  postLogIn,
  postReview,
  postRegister,
  getUser,
  getUserById,
}

export default Api
