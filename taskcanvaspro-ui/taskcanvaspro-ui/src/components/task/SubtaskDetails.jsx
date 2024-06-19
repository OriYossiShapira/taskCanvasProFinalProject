import './SubtaskDetails.css'

const SubtaskDetails = ({subTask}) => {

    return(
        <div className="subtask-details">
            <h4>{subTask.title}</h4>
            <div>
                <p>Description:</p>
                <p>{subTask.description}</p>
            </div>
            <div>
                <p>Done:</p>
                <p>{subTask.done?'true':'false'}</p>
            </div>
        </div>
    )
}

export default SubtaskDetails;