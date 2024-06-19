
import './EditSubtask.css'

const EditSubtask = ({ subtask, handleChange, handleDelete }) => {

    return (
        <div className='edit-subtask'>
            <div>
                <label htmlFor={`title${subtask.id}`}>Title</label>
                <input type="text" id={`title${subtask.id}`} value={subtask.title} onChange={({ target }) => handleChange('title', target.value, subtask.id)} required />
            </div>
            <div>
                <label htmlFor={`description${subtask.id}`}>Description</label>
                <input type="text" id={`description${subtask.id}`} value={subtask.description} onChange={({ target }) => handleChange('description', target.value, subtask.id)} required />
            </div>
            <div>
                <label htmlFor={`done${subtask.id}`}>Done</label>
                <input type="checkbox" id={`done${subtask.id}`} checked={subtask.done} value={subtask.done} onChange={({ target }) => handleChange('done', target.checked, subtask.id)} />
            </div>
            <div className='delete-subtask_container'>
                <button type="button" onClick={() => handleDelete(subtask.id)}>X</button>
            </div>
        </div>
    )
}

export default EditSubtask;