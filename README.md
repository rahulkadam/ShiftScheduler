# Shift Scheduler
ShiftScheduler

Shift Scheduler is a small service which help to create schedule for company.
Purpose to solve manual shift scheduling issue.

**How to use:**  
Shift Scheduler service needs 3 input to generate schedule.  
**1. Employee Count available**  
  : Employee Pool which is available to schedule (Currently we are accepting only count and generating dummy user of that count)  
**2. Time Span (No of days Schedule)**  
   : Total No of days's for which schedule has to generate.  
**3. No of Shift per Day**  
   : Total Number of Shift per day.

**Rules Validator**  
ShiftSchedule service comes up with no of rule to apply on it.
We can customize our rules and decide scheudle.
To add Rules , we just need to create Rules and then add it into default Rule List.

**Things TODO:**
1. Service should accept Rule on the fly without server restart. Customization and hook should be provided.  
2. Service Should accept holidays , so we will able to skip those days from schedule generation.  
3. Employee List should be taken from available DB than generating dummy.  

**Access**  
Shift Scheduler service is deployed on EC2 :  
[Shift Scheduler Access Url](http://34.208.45.239:9991/)
  
   
