import './App.css';
import api from './api/axiosConfig';
import { useState, useEffect } from 'react';
import Layout from './components/Layout';
import { Route, Routes } from 'react-router-dom';
import Home from './components/home/Home';
import Header from './components/header/Header';
import Trailer from './components/trailer/Trailer';
import Review from './components/reviews/Reviews';

function App() {

  const [videos, setVideos] = useState([]);
  const [video, setVideo] = useState();
  const [reviews, setReviews] = useState([]);

  const getVideos = async () => {

    try {
      const response = await api.get("/api/v1/videos");
      console.log(response.data);
      setVideos(response.data);
    }
    catch (err) {
      console.log(err);
    }

  }

  const getMovieData = async(movieId) => {
    try {
      const response = await api.get(`/api/v1/movies/${movieId}`)
      
      const singleMovie = response.data;

      setVideo(singleMovie);

      setReviews(singleMovie.reviews);
    
    } catch (error) {
      
    }
  }

  useEffect(() => {
    getVideos();
  
  },[])

  return (
    <div className="App">
      <Header/>
      
      <Routes>
        <Route path='/' element={<Layout/>}>
          <Route path='/' element={<Home videos={videos}/>}></Route>
          <Route path='/Trailer/:ytTrailerId' element={<Trailer/>}></Route>
          <Route path='/Reviews/:movieId' element = {<Review getMovieData = {getMovieData} video={video} reviews={reviews} setReviews = {setReviews}/>}></Route>
        </Route>
      </Routes>

    </div>
  );
}

export default App;