package org.sakaiproject.contentreview.impl;

import org.sakaiproject.genericdao.api.CompleteGenericDao;

/**
 * This is a specialized DAO that allows the developer to extend
 * the functionality of the generic dao package
 * @author Sakai App Builder -AZ
 */
public interface ContentReviewDao extends CompleteGenericDao {

	/**
	    * Allows a lock to be obtained that is system wide,
	    * this is primarily for ensuring something runs on a single server only in a cluster<br/>
	    * <b>NOTE:</b> This intentionally returns a null on failure rather than an exception since exceptions will
	    * cause a rollback which makes the current session effectively dead, this also makes it impossible to 
	    * control the failure so instead we return null as a marker
	    * 
	    * @param lockId the name of the lock which we are seeking
	    * @param holderId a unique id for the holder of this lock (normally a server id)
	    * @param timePeriod the length of time (in milliseconds) that the lock should be valid for,
	    * set this very low for non-repeating processes (the length of time the process should take to run)
	    * and the length of the repeat period plus the time to run the process for repeating jobs
	    * @return true if a lock was obtained, false if not, null if failure
	    */
	   @SuppressWarnings("unchecked")
	   public Boolean obtainLock(String lockId, String executerId, long timePeriod);
	   
	   /**
	    * Releases a lock that was being held,
	    * this is useful if you know a server is shutting down and you want to release your locks early<br/>
	    * <b>NOTE:</b> This intentionally returns a null on failure rather than an exception since exceptions will
	    * cause a rollback which makes the current session effectively dead, this also makes it impossible to 
	    * control the failure so instead we return null as a marker
	    * 
	    * @param lockId the name of the lock which we are seeking
	    * @param holderId a unique id for the holder of this lock (normally a server id)
	    * @return true if a lock was released, false if not, null if failure
	    */
	   @SuppressWarnings("unchecked")
	   public Boolean releaseLock(String lockId, String executerId);
	
	
}