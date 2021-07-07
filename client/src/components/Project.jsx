import React,{Component} from "react"
import { Formik, Form, Field, ErrorMessage } from "formik"
import moment from "moment"
import ProjectService from "../api/ProjectService"

class TodoComponent extends Component{
    constructor(props){
        super(props)
        this.state = {
            id: this.props.match.params.id,
            projectName:"",
            description: '',
            status:''
        }
        this.onSubmit = this.onSubmit.bind(this)
    }
    validate(values){
        let error = {}
        if(!values.projectName){
            error = "Enter value for description"
        }
        else if(!values.description){
            error = "Enter value for description"
        }else if(values.description.length<5){
            error.description = "Description must at least 5 characters"
        }

        if(!values.status){
            error.targetDate= "Enter valid date"
        }
        return error
    }
   
    onSubmit(values){
       
       let project = {
        id: this.state.id,
        projectName:values.projectName,
        description: values.description,
        status: values.status
       }
        if(this.state.id===-1){
            ProjectService.postProject(project).then(()=>this.props.history.push("/projects"))
        }else{
        
        ProjectService.updateProjectById(this.state.id,project).then(()=> this.props.history.push("/projects"))
    }
    }
    componentDidMount(){
        let id = this.props.match.params.id
        if(id===-1){
            return
        }
       
        
        ProjectService.getProjectById(id)
        .then(response=>
            this.setState ({
                projectName: response.data.projectName,
                description: response.data.description,
                status : response.data.status
            })
        )
    }
    render(){
        let projectName = this.state.projectName
        let description = this.state.description
        let status = this.state.status
        return (
        <div>
            <h3>Project For ID: {this.props.match.params.id}</h3>
            <div className="container">
            <Formik 
                initialValues={{
                    projectName,
                    description,
                    status
                }}
                validateOnChange = {false}
                validateOnBlur = {false}
                onSubmit = {this.onSubmit}
                validate = {this.validate}
                enableReinitialize={true}
                
            >
                {
                    (props)=>(
                        <Form>
                            <ErrorMessage name="description" component="div" className="alert alert-warning" />
                            <ErrorMessage name="status" component="div" className="alert alert-warning" />
                            <fieldset className="form-group">
                                <label>Description</label>
                                <Field className="form-control" type="text" name="description" />
                            </fieldset>
                            <fieldset className="form-group">
                                <label>Status</label>
                                <Field className="form-control" type="text" name="status" />
                                <Field as="select" name="status">
                                    <option value="in progress">inProgress</option>
                                    <option value="completed">completed</option>
                                    <option value="created">created</option>
                                    <option value="on hold">on hold</option>
                                </Field>                            </fieldset>
                            <button className="btn btn-success" type="submit">Save</button>
                        </Form>
                    )
                }
            </Formik>
            </div>
        </div>
        )
    }
}

export default TodoComponent