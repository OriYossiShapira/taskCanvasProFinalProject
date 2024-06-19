import {Link} from 'react-router-dom';

import './TaskMenu.css'
const TaskMenu = ({onLogout}) => {

  return (
    <div className='menu'>
      <Link to="/"  onClick={onLogout}>LOGOUT</Link>
      <Link to="/add-task">ADD TASK</Link>
      <Link to="/tasks">TASKS</Link>
    </div>
  )
}

export default TaskMenu;