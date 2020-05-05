package routers

import io.vertx.reactivex.ext.web.Router
import io.vertx.reactivex.ext.web.RoutingContext
import io.vertx.reactivex.ext.web.handler.BodyHandler
import lab2.DB
import lab2.LogDBDecorator
import lab2.bean.CreateTableRequest
import lab2.bean.InsertRowRequest
import lab2.bean.JoinRowRequest
import lab2.bean.SelectRowRequest
import mapBodyTo
import respondJson
import respondSuccess
import vertx

val lab2Router: Router = Router.router(vertx).apply {
    get("/logs").handler(::getLogs)

    route().handler(BodyHandler.create())
    post("/create").handler(::createTable)
    post("/insert").handler(::insertRow)
    post("/select").handler(::selectRow)
    post("/join").handler(::joinTable)
    post("/logs/clear").handler(::clearLogs)
}

val decorator = LogDBDecorator(DB())

fun clearLogs(context: RoutingContext) {
    decorator.clearLogs()
    context.respondSuccess()
}

fun getLogs(context: RoutingContext) {
    context.respondJson(decorator.logs)
}

fun createTable(context: RoutingContext) {
    context.apply {
        mapBodyTo(CreateTableRequest::class.java) {
            decorator.createTable(it.table_name, it.col)
            respondSuccess()
        }
    }
}

fun insertRow(context: RoutingContext) {
    context.apply {
        mapBodyTo(InsertRowRequest::class.java) {
            decorator.insert(it.table_name, it.data)
            respondSuccess()
        }
    }
}

fun selectRow(context: RoutingContext) {
    context.apply {
        mapBodyTo(SelectRowRequest::class.java) {
            decorator.select(it.table_name, it.col, it.select)
            respondSuccess()
        }
    }
}

fun joinTable(context: RoutingContext) {
    context.apply {
        mapBodyTo(JoinRowRequest::class.java) {
            decorator.join(it.tab1_name, it.tab2_name, it.tab1_col, it.tab2_col)
            respondSuccess()
        }
    }
}