import { useState, useEffect, useContext } from 'react';
import { getTaskList } from '../../api/taskApi'
import AppContext from '../../AppContext';
import TaskItem from './TaskItem';
import './TaskList.css'
const TaskList = () => {
    const [tasks, setTasks] = useState([]);
    const [filter, setFilter] = useState(null)
    const [error, setError] = useState(null);
    const context = useContext(AppContext)

    const updateTaskList = async () => {

        const result = await getTaskList();
        if (result.ok) {
            setError(null);
            context.unflagUpdate();
            result.data.tasks.sort((t1, t2) => t1.deadline.localeCompare(t2.deadline))
            setTasks(result.data.tasks)
        } else {
            setError(result.message)
        }
    }
    useEffect(() => {
        updateTaskList();
 
    }, [])

    useEffect(() => {
        if (context.doUpdate === true) {
            updateTaskList();
        }
    }, [context.doUpdate])

        const doFilter = (task) => {
            if(filter == null){
                return true;
            }
            return task.completed === filter;
        }
    return (
        <div className='task-list'>
            <div className='filter'>
                <button onClick={() => setFilter(null)}>ALL TASKS</button>
                <button onClick={() => setFilter(false)}>OPEN TASKS</button>
                <button  onClick={() => setFilter(true)}>CLOSED TASKS</button>
            </div>
            <div>
                {error !== null && <div>{error}</div>}
                {
                    tasks.filter(doFilter).map(t => <TaskItem key={t.id} task={t} />)
                }
            </div>
        </div>
    )
}

export default TaskList;