# MySQL Setup Guide for Issue Tracker

## 🚀 **Quick Setup (Windows)**

### **Step 1: Install MySQL**
1. Download MySQL Community Server from: https://dev.mysql.com/downloads/mysql/
2. Install with default settings
3. Set root password during installation (use: `root`)

### **Step 2: Create Database**
```sql
-- Open MySQL Command Line Client or MySQL Workbench
CREATE DATABASE issuetracker;
USE issuetracker;
```

### **Step 3: Update Configuration**
The application is already configured to use MySQL. If you need to change credentials, edit `src/main/resources/application.properties`:

```properties
spring.datasource.username=root
spring.datasource.password=your_mysql_password
```

### **Step 4: Run Application**
```bash
mvn spring-boot:run
```

## 🔧 **Alternative: Using MySQL Workbench**

1. **Open MySQL Workbench**
2. **Create New Connection:**
   - Connection Name: `Issue Tracker`
   - Hostname: `localhost`
   - Port: `3306`
   - Username: `root`
   - Password: `root` (or your MySQL password)

3. **Create Database:**
   ```sql
   CREATE DATABASE issuetracker;
   ```

## 📊 **Database Schema**
The application will automatically create the `issues` table with:
- `id` (Primary Key)
- `title` (VARCHAR)
- `description` (TEXT)
- `status` (ENUM: OPEN/CLOSED)
- `assigned_user` (VARCHAR)
- `created_at` (TIMESTAMP)
- `updated_at` (TIMESTAMP)

## 🌐 **Access Your Application**
- **Web Interface**: http://localhost:8080
- **REST API**: http://localhost:8080/api/issues

## 🚨 **Troubleshooting**

### **Connection Error?**
1. Check if MySQL is running: `services.msc` → MySQL
2. Verify credentials in `application.properties`
3. Check if port 3306 is available

### **Database Not Found?**
```sql
CREATE DATABASE issuetracker;
```

### **Permission Denied?**
```sql
GRANT ALL PRIVILEGES ON issuetracker.* TO 'root'@'localhost';
FLUSH PRIVILEGES;
```

## 🎯 **Production Deployment**
For production, update these in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://your-server:3306/issuetracker
spring.datasource.username=your_prod_user
spring.datasource.password=your_prod_password
```


