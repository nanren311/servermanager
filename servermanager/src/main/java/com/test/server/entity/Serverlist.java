package com.test.server.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author chaowu
 * @since 2021-09-30
 */

public class Serverlist implements Serializable {

	public static final long serialVersionUID = 1L;
	public int id;

	public String servername;
	public String serveruser;
	public String status;

	public String type;

	public String location;
	public String serverreservetime;
	public String serverstarttime;
	public String serverendtime;

	public String gputype;

	public String gpuuser;
	public String healthystatus;

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}
	public String getServeruser() {
		return serveruser;
	}

	public void setServeruser(String serveruser) {
		this.serveruser = serveruser;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	public String getServerstarttime(){
		return serverstarttime;
	}

	public void setServerstarttime(String serverstarttime) {
		this.serverstarttime = serverstarttime;
	}
	public String getServerendtime(){
		return serverendtime;
	}

	public void setServerendtime(String serverendtime) {
		this.serverendtime = serverendtime;
	}
	public String getServerreservetime(){
		return serverreservetime;
	}

	public void setServerreservetime(String serverreservetime) {
		this.serverreservetime = serverreservetime;
	}

	public String getHealthystatus() {
		return healthystatus;
	}

	public void setHealthystatus(String healthystatus) {
		this.healthystatus = healthystatus;
	}
	public String getGpuuser() {
		return gpuuser;
	}

	public void setGpuuser(String gpuuser) {
		this.gpuuser = gpuuser;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGputype() {
		return gputype;
	}

	public void setGputype(String gputype) {
		this.gputype = gputype;
	}

	@Override
	public String toString() {
		return "Serverlist [id=" + id + ", servername=" + servername + ", serveruser=" + serveruser + ", type="
				+ type + ", location=" + location + ", serverreservetime=" + serverreservetime + ",serverstarttime=" + serverstarttime + ", serverendtime=" + serverendtime + ", gputype=" + gputype
				+ ", gpuuser=" + gpuuser + ", healthystatus=" + healthystatus + "]";
	}

}
