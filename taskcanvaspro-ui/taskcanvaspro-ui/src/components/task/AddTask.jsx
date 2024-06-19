import { useEffect, useState, useContext } from "react";
import { postTask } from "../../api/taskApi";
import AppContext from '../../AppContext';
import { useNavigate } from "react-router-dom";
import './AddTask.css'

const AddTask = () => {
    const nav = useNavigate();
    const [task, setTask] = useState({subTasks:[], title: '', description: '', deadline: '',  completed: false, progress: 0 })
    const [error, setError] = useState(null);
    const context = useContext(AppContext);

    const handleChange = (event) => {
        const fieldName = event.target.id;
        const value = event.target.value;
        setTask({ ...task, [fieldName]: value })
    }
    useEffect(() => {
        const date = new Date().toISOString().split('T')[0];
        setTask({ ...task, deadline:date })
    }, [])

    const handleSubmit = async(event) => {
        event.preventDefault();

        const newTask = {...task};        
        newTask.user = context.getUser();
        const result = await postTask(newTask);
        console.log('done', result);
        if(result.ok){
            setError(null);
            context.flagUpdate();
            nav("/tasks")
        }else{
            setError(result.message)
        }
    }

    return (
        <form onSubmit={handleSubmit} className="add-task">
            {error !== null && <div>{error}</div>}
            <div>
                <label htmlFor="Title">Title</label>
                <input type="text" id="title" value={task.title} onChange={handleChange} required />
            </div>
            <div>

                <label htmlFor="description">Desription</label>
                <input type="text" id="description" value={task.description} onChange={handleChange} required />
            </div>
            <div>

                <label htmlFor="deadline">deadline</label>
                <input type="date" id="deadline" value={task.deadline} onChange={handleChange} required />
            </div>
            <div>
                <button>ADD</button>
            </div>

        </form>
    )
}

export default AddTask;