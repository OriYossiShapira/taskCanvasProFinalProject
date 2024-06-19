import {Link} from 'react-router-dom';
import './TaskItem.css'

const TaskItem = ({task}) => {
    
    return(
        <Link to={`/tasks/${task.id}`} className='task-item'>
            <h3>{task.title}</h3>
            <p>{task.deadline}</p>
            <p>{task.description}</p>
            <p>{task.completed?"Completed":"In progress"}</p>
        </Link>
    )
}

export default TaskItem;