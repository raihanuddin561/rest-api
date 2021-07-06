import React,{Component} from "react"
import ProjectService from "../api/ProjectService"
import moment from "moment"
class ProjectComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            projects:[],
            message: null
        }
        this.refreshProjects = this.refreshProjects.bind(this)
        this.deleteProject = this.deleteProject.bind(this)
        this.updateProject = this.updateProject.bind(this)
        this.addProject = this.addProject.bind(this)
    }
    componentDidMount(){
        this.refreshProjects()
    }

    refreshProjects(){
       
        ProjectService.getProjectList()
        .then(response=>{
            
            console.log(response)

            this.setState({
                projects: response.data
            })
        })
        .catch(

            ()=>{
               
            }
        )
       
    }
    deleteProject(id){
        
        ProjectService.deleteProjectById(id)
        .then(response=>{
            this.setState({
                message: "Successfully deleted ID : "+id
            })
            this.refreshProjects()
        })
    }
    addProject(){
        
       // this.props.history.push(`/project/-1`)
    }
    updateProject(id){
        console.log(`update id ${id}`)
        this.props.history.push(`/project/${id}`)
        // let username = Authentication.getLoggedIn()
        // projectservice.updateProjectList(username,id)
        // .then(response=>{
        //     this.setState({
        //         message: "Successfully deleted ID : "+id
        //     })
        //     this.refreshProjects()
        // })
    }
    render(){
        return (
            <>
                <h1>project List</h1>
        {this.state.message && <div className="alert alert-warning">{this.state.message}</div>}
               <div className="container">
                <table className="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Project Name</th>
                                <th>Status</th>
                                
                            </tr>
                        </thead>
                        <tbody>
                        { 
                            this.state.projects.map(
                                project=>
                                <tr key={project.projectId}>
                                <td>{project.projectId}</td>
                            <td>{project.projectName}</td>
                            <td>{project.status.toString()}</td>
                            <td><button className="btn btn-success" onClick={()=>this.updateProject(project.id)}>Update</button></td>
                            <td><button className="btn btn-danger" onClick={()=>this.deleteProject(project.id)}>Delete</button></td>
                            
                            </tr>
                            )
                            }
                        </tbody>
                    </table>
                    <button className="btn btn-success" onClick={()=>this.addProject()}>Add</button>
               </div>
            </>
        )
    }
}
export default ProjectComponent