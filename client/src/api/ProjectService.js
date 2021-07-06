import axios from "axios"
import {api_url} from "../Constants"
class ProjectService{
    getProjectList(){
        
        return axios.get(`${api_url}/projects`)
        
    }
    deleteProjectById(id){
        return axios.delete(`${api_url}/projects/${id}`)
    }

    getProjectById(id){
        return axios.get(`${api_url}/projects/${id}`)
    }
    updateProjectById(id,projects){
         
        return axios.put(`${api_url}/projects/${id}`,projects)
    }

    postTodo(projects){
        return axios.post(`${api_url}/projects/`,projects)
    }
}
export default new ProjectService()