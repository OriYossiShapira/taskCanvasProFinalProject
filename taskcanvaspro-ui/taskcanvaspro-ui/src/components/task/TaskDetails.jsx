import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getTask } from "../../api/taskApi";
import SubtaskDetails from "./SubtaskDetails";
import './TaskDetails.css'

const TaskDetails = () => {
    const params = useParams();
    const nav = useNavigate();

    const [task, setTask] = useState(null)

    useEffect(() => {
        getTask(params.id).then(task => {
            setTask(task.data)
        })
    }, [])

    const handleEditTask = () => {
        nav(`/edit-task/${task.id}`)
    }

    const calculateProgress = () => {
        let countDone = 0;
        for(const sub of task.subTasks){
            if(sub.done){
                countDone++;
            }
        }
        return countDone/task.subTasks.length*100|0;
    }

    if (task === null) {
        return <p>loading task....</p>
    }

    return (
        <div className='task-details'>
            <div>
                <p>showing task # {params.id}</p>
                <h3>{task.title}</h3>
                <h4>{task.description}</h4>
                <p>Deadline: {task.deadline}</p>
                <p>Status: {task.completed?'done':'open'}</p>
                <p>Progress: {calculateProgress().toFixed(0)}%</p>
                <div className='subtasks-details'>
                    <h3>sub tasks</h3>
                {
                    task.subTasks.map(st => <SubtaskDetails key={st.id} subTask={st} />)
                }
                </div>

                <button onClick={handleEditTask}>EDIT</button>
            </div>
        </div>
    )
}

export default TaskDetails;