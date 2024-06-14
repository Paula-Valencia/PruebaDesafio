package org.example

// Clase SmartDevice
open class SmartDevice(val name: String, val category: String, val deviceType: String) {
    var deviceStatus: String = "off"

    open fun printDeviceInfo() {
        println("Device name: $name, category: $category, type: $deviceType")
    }
}

// Clase SmartTvDevice
class SmartTvDevice(name: String, category: String, deviceType: String) : SmartDevice(name, category, deviceType) {
    var volume: Int = 50
    var channel: Int = 1

    fun decreaseVolume() {
        if (deviceStatus == "on") {
            volume -= 10
            println("Volume decreased. Current volume: $volume")
        } else {
            println("Cannot adjust volume. TV is off.")
        }
    }

    fun previousChannel() {
        if (deviceStatus == "on") {
            channel -= 1
            println("Switched to previous channel. Current channel: $channel")
        } else {
            println("Cannot change channel. TV is off.")
        }
    }
}

// Clase SmartLightDevice
class SmartLightDevice(name: String, category: String, deviceType: String) : SmartDevice(name, category, deviceType) {
    var brightness: Int = 100

    fun decreaseBrightness() {
        if (deviceStatus == "on") {
            brightness -= 10
            println("Brightness decreased. Current brightness: $brightness")
        } else {
            println("Cannot adjust brightness. Light is off.")
        }
    }
}

// Clase SmartHome
class SmartHome {
    private val devices: MutableList<SmartDevice> = mutableListOf()
    var deviceTurnOnCount: Int = 0

    fun addDevice(device: SmartDevice) {
        devices.add(device)
    }

    fun toggleDevice(deviceName: String, status: String) {
        val device = devices.find { it.name == deviceName }
        if (device != null) {
            device.deviceStatus = status
            if (status == "on") {
                deviceTurnOnCount++
            }
        }
    }

    fun decreaseTvVolume() {
        val tv = devices.filterIsInstance<SmartTvDevice>().firstOrNull()
        tv?.decreaseVolume()
    }

    fun changeTvChannelToPrevious() {
        val tv = devices.filterIsInstance<SmartTvDevice>().firstOrNull()
        tv?.previousChannel()
    }

    fun printSmartTvInfo() {
        val tv = devices.filterIsInstance<SmartTvDevice>().firstOrNull()
        tv?.printDeviceInfo()
    }

    fun printSmartLightInfo() {
        val light = devices.filterIsInstance<SmartLightDevice>().firstOrNull()
        light?.printDeviceInfo()
    }

    fun decreaseLightBrightness() {
        val light = devices.filterIsInstance<SmartLightDevice>().firstOrNull()
        light?.decreaseBrightness()
    }
}

fun main() {
    val home = SmartHome()

    val tv = SmartTvDevice("Televisor Sala", "Entretenimiento", "Televisión")
    val light = SmartLightDevice("Luz Sala", "Automatización del Hogar", "Iluminación")

    home.addDevice(tv)
    home.addDevice(light)

    home.toggleDevice("Televisor Sala", "on")
    home.toggleDevice("Luz Sala", "on")

    home.decreaseTvVolume()
    home.changeTvChannelToPrevious()
    home.printSmartTvInfo()
    home.printSmartLightInfo()
    home.decreaseLightBrightness()
}
