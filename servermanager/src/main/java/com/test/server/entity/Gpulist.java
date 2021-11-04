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

public class Gpulist implements Serializable {

	public static final long serialVersionUID = 1L;
	public int id;

	public String gpulocation;
	public String gpunm;
	public String gputype;
	public String gpustatus;


	public String gpustarttime;
	public String gpuendtime;

	public String gpuuser;
	public String healthystatus;



	public String getGpunm() {
		return gpunm;
	}

	public void setGpunm(String gpunm) {
		this.gpunm = gpunm;
	}

	


	public String getGpulocation() {
		return gpulocation;
	}

	public void setGpulocation(String gpulocation) {
		this.gpulocation = gpulocation;
	}
	public String getGpustarttime(){
		return gpustarttime;
	}

	public void setGpustarttime(String gpustarttime) {
		this.gpustarttime = gpustarttime;
	}
	public String getGpuendtime(){
		return gpuendtime;
	}

	public void setGpuendtime(String gpuendtime) {
		this.gpuendtime = gpuendtime;
	}


	public void setGpustatus(String gpustatus) {
		this.gpustatus = gpustatus;
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
		return "Gpulist [id=" + id + ", gpuuser=" + gpuuser + ", gputype=" + gputype + ", gpulocation=" + gpulocation + ", gpustatus=" + gpustatus + ",gpustarttime=" + gpustarttime + ", gpuendtime=" + gpuendtime + ", gpuuser=" + gpuuser + ", healthystatus=" + healthystatus + "]";
	}

}
