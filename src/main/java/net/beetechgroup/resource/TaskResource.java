package net.beetechgroup.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import net.beetechgroup.entity.Task;
import net.beetechgroup.repository.TaskRepository;
import net.beetechgroup.resource.input.TaskInput;
import net.beetechgroup.resource.output.TaskOutput;

@Path("/task")
@ApplicationScoped
public class TaskResource {

    private final TaskRepository taskRepository;

    public TaskResource(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @POST
    @Transactional
    public Response create(TaskInput taskInput) {
        Task task = new Task(taskInput);
        this.taskRepository.persist(task);
        return Response.created(URI.create("/task/" + 1)).entity(task.toOutput()).build();
    }

    @GET
    public Response retrieveAll() {
        List<Task> tasks = this.taskRepository.listAll();
        List<TaskOutput> list = tasks.stream().map(Task::toOutput).toList();
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public Response retrieveById(@PathParam("id") UUID id) {
        Task task = this.taskRepository.findById(id);
        TaskOutput output = task.toOutput();
        return Response.ok(output).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateById(@PathParam("id") UUID id, TaskInput taskInput) {
        Task task = new Task(taskInput);
        task.setId(id);
        return Response.ok(task.toOutput()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") UUID id) {
        Task task = this.taskRepository.findById(id);
        if(Boolean.TRUE == this.taskRepository.deleteById(id)){
            return Response.ok(task).build();
        } else {
            return Response.status(404).build();
        }
    }

}
