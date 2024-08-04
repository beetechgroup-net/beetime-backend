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
import net.beetechgroup.resource.input.TaskInput;
import net.beetechgroup.service.CreateTaskService;
import net.beetechgroup.service.DeleteTaskByIdService;
import net.beetechgroup.service.FindAllTaskService;
import net.beetechgroup.service.FindTaskByIdService;
import net.beetechgroup.service.UpdateTaskService;

@Path("/tasks")
@ApplicationScoped
public class TaskResource {

    private final CreateTaskService createTaskService;
    private final UpdateTaskService updateTaskService;
    private final FindAllTaskService findAllTaskService;
    private final FindTaskByIdService findTaskByIdService;
    private final DeleteTaskByIdService deleteTaskByIdService;

    public TaskResource(UpdateTaskService updateTaskService,
            CreateTaskService createTaskService, FindAllTaskService findAllTaskService,
            FindTaskByIdService findTaskByIdService, DeleteTaskByIdService deleteTaskByIdService) {
        this.updateTaskService = updateTaskService;
        this.createTaskService = createTaskService;
        this.findAllTaskService = findAllTaskService;
        this.findTaskByIdService = findTaskByIdService;
        this.deleteTaskByIdService = deleteTaskByIdService;
    }

    @POST
    @Transactional
    public Response create(TaskInput taskInput) {
        Task task = this.createTaskService.execute(taskInput);
        return Response.created(URI.create("/task/" + 1)).entity(task.toOutput()).build();
    }

    @GET
    public Response retrieveAll() {
        List<Task> tasks = this.findAllTaskService.execute();
        return Response.ok(tasks.stream().map(Task::toOutput).toList()).build();
    }

    @GET
    @Path("/{id}")
    public Response retrieveById(@PathParam("id") UUID id) {
        Task task = this.findTaskByIdService.execute(id);
        return Response.ok(task.toOutput()).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateById(@PathParam("id") UUID id, TaskInput taskInput) {
        Task task = this.updateTaskService.execute(id, taskInput);
        return Response.ok(task.toOutput()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") UUID id) {
        Task task = this.deleteTaskByIdService.execute(id);
        return Response.ok(task.toOutput()).build();
    }

}
