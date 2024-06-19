import { useEffect, useState, useContext } from 'react';
import Login from './components/Login';
import Register from './components/Register';
import TaskList from './components/task/TaskList';
import TaskDetails from './components/task/TaskDetails';
import AuthMenu from './components/menu/AuthMenu';
import TaskMenu from './components/menu/TaskMenu';
import { Routes, Route, redirect } from 'react-router-dom';
import './App.css';
import AppContext from './AppContext';
import AddTask from './components/task/AddTask';
import EditTask from './components/task/EditTask';
import Home from './components/Home';

function App() {
  const context = useContext(AppContext);

  useEffect( () => {
    const strUser = localStorage.getItem("user");
    if(strUser){
      const user = JSON.parse(strUser)
      // setIsLoggedin(true);
    }
  }, [])
  const onLogout = () => {
    context.onLogout();
    // setIsLoggedin(false);

  }
  return (
    <div className="App">
      {
        context.isLoggedin() ? (
          <TaskMenu onLogout={onLogout} />
        ) : (
          <AuthMenu />
        )
      }

      <Routes>
      <Route path="/" element={<Home />} />
        {
          context.isLoggedin() ? (
            <>
            <Route path="/login" element={<Login />} />
            <Route path="/tasks" element={<TaskList/>} />
            <Route path="/tasks/:id" element={<TaskDetails />} />
            <Route path="/add-task" element={<AddTask />} />
            <Route path="/edit-task/:id" element={<EditTask />} />    
            </>
          ):(<>
              <Route path="/login" element={<Login />} />
              <Route path="/register" element={<Register />} />
          </>)
        }
          
      </Routes>
    </div>
  );
}

export default App;
