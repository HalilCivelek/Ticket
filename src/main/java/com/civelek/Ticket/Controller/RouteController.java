package com.civelek.Ticket.Controller;

import com.civelek.Ticket.Entity.Route;
import com.civelek.Ticket.IService.IRouteImpl;
import com.civelek.Ticket.util.VTUtil;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private IRouteImpl routeImpl;

    @PostMapping("/saveRoute")
    public ResponseEntity<Route> saveCustomer(@Valid @RequestBody Route route){
        return ResponseEntity.ok(routeImpl.saveRoute(route));
    }

    @GetMapping("/getRoute")
    @ResponseBody
    public void getRoute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Long routeId = VTUtil.reqGetLong(request.getParameter("routeId"),null);
        String departureName = VTUtil.reqGetString(request.getParameter("departureName"),null);
        String arrivalName = VTUtil.reqGetString(request.getParameter("arrivalName"),null);

        JSONObject sendJson = new JSONObject();
       List<Route> route = routeImpl.getRoute(routeId, departureName, arrivalName);

        sendJson.put("route",route);
        response.getWriter().write(sendJson.toString());
    }
}
