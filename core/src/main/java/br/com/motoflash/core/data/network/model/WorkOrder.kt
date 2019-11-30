package br.com.motoflash.core.data.network.model


data class WorkOrder(
    var id: String? = null,
    var near: Boolean? = null,
    var shortHash: String? = null,
    var userId: String? = null,
    var priority: String? = null,
    var trackingCode: String? = null,
    var price: Double? = null,
    var courierPrice: Double? = null,
    var totalPoints: Int? = null,
    var scheduledDate: Long? = null,
    var distance: Double? = null,
    var duration: Double? = null,
    var status: String? = null,
    var user: User? = null,
    var courierId: String? = null,
    var courier: Courier? = null,
    var currentPoint: Long? = null,
    var quotation: Quotation? = null,
    var points: List<WorkOrderPoint>? = null,
    var modifiedDate: Long? = null,
    var bonus: Double? = null,
    var createdDate: Long? = null
) {
    enum class Status {
        PENDING, ASSIGNED, EXECUTION, CANCELED, FINISHED, ALL
    }

    enum class Priority {
        NORMAL, URGENT
    }
}