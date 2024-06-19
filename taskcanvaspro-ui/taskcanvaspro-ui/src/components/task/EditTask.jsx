import { useEffect, useState, useContext } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getTask,putTask } from "../../api/taskApi";
import AppContext from '../../AppContext';
import './EditTask.css'
import EditSubtask from "./EditSubtask";


const subTask = {id:0,title:'', description:''}

const EditTask = () => {
    const params = useParams();
    const nav = useNavigate();
    const [task, setTask] = useState({subTasks:[], title: '', description: '', deadline: '',  completed: false, progress: 0 })
    const [error, setError] = useState(null);
    const context = useContext(AppContext);

    /* event handlers */
    const handleAddSubtask = () => {
        const t = {...subTask}
        t.id = (task.subTasks.length + 1) * (-1)
        const temp = [...task.subTasks, t];
        setTask({ ...task, subTasks:temp })
    }
    const handleDeleteSubTask = (id) =>{
        const temp = task.subTasks.filter(st => st.id !== id);
        setTask({ ...task, subTasks:temp })
    }
    const handleSubTaskChange = (fieldName, value, id) => {

        const taskIndex = task.subTasks.findIndex(st => st.id === id);
        const temp = [...task.subTasks]
        temp[taskIndex][fieldName] = value;
        setTask({...task, subTasks:temp})
    }
    const handleChange = (event) => {
        const fieldName = event.target.id;
        const value = event.target.value;
        setTask({ ...task, [fieldName]: value })
    }

    const handleCheckboxChange = (event) => {
        const fieldName = event.target.id;
        const value = event.target.checked;
        setTask({ ...task, [fieldName]: value })
    }

    /* component load */
    useEffect(() => {
        getTask(params.id).then( serverTask => {
            serverTask.data.progress = 1;
            setTask(serverTask.data)

        })
    }, [])

    /* component save */
    const handleSubmit = async(event) => {
        event.preventDefault();
        const strUser = localStorage.getItem("user");
        const updatedTask = {...task};
        for(const t of updatedTask.subTasks){
            if(t.id < 0){
                t.id = undefined
            }
        }
        if(strUser){
            updatedTask.user = JSON.parse(strUser)
        }
        
        const result = await putTask(updatedTask);
        console.log('done', result);
        if(result.ok){
            setError(null);
            context.flagUpdate();
            nav("/tasks")
        }else{
            setError(result.message)
        }
    }

    if(!task){
        return <div>Loading, please wait</div>
    }
    return (
        <form onSubmit={handleSubmit} className="edit-task">
            {error !== null && <div>{error}</div>}
            <div>

                <label htmlFor="Title">Title</label>
                <input type="text" id="title" value={task.title} onChange={handleChange} required />
            </div>
            <div>

                <label htmlFor="desription">Desription</label>
                <input type="text" id="description" value={task.description} onChange={handleChange} required />
            </div>
             <div>

                <label htmlFor="deadline">deadline</label>
                <input type="date" id="deadline" value={task.deadline} onChange={handleChange} required />
            </div>
            <div>
                <label htmlFor="completed">Completed</label>
                <input type="checkbox" id="completed" checked={task.completed} value={task.completed} onChange={handleCheckboxChange}  />
            </div>
            <h3>SUB TASKS</h3>
            <div>
                <button type="button" onClick={handleAddSubtask}>+</button>
            </div>
            <div className="edit-subtasks">
            {
                task.subTasks.sort( (st1, st2)=> st1.id - st2.id).map( st => <EditSubtask key={st.id} subtask={st} handleChange={handleSubTaskChange} handleDelete={handleDeleteSubTask}/>)
            }
            
            </div>
            
            <button>UPDATE</button>
        </form>
    )
}

export default EditTask;