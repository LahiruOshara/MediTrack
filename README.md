# MediTrack
## Deployment Architecture

<img width="1512" alt="Screenshot 2025-01-16 at 15 13 13" src="https://github.com/user-attachments/assets/0427965e-6eb6-43e7-911d-a201e89a19b7" />

## Test Automation

### Tools & Approach:
- JUnit 5 (Java) for unit and integration testing.
- GitHub Actions automate the testing process.

### Testing Breakdown:
#### Unit Tests:
-  Focus on the Service layer using JUnit 5 (Spring Boot included).

#### Integration Tests:
- Cover the Controller layer (e.g., PatientController).

#### Workflow Automation:
- Trigger: Pull request (PR) to the main branch triggers GitHub Action.
- Process:
-- Builds the application.
-- Runs all tests.
- Merge Condition: Code merges only if all tests pass.

## CI/CD Pipeline

<img width="732" alt="Screenshot 2025-01-16 at 15 13 53" src="https://github.com/user-attachments/assets/8f8aaf4e-f34f-40ac-9a60-83e6ffae5191" />

# Pipeline Flow

1. **Code Push**: Developers push code to **GitHub**.
2. **Workflow Trigger**: On merge to the main branch, **GitHub Actions** build the JAR and Docker image.
3. **Docker Image**: Built image is pushed to **Docker Hub**.
4. **Deployment to Kubernetes**:
   - Deploy to **AWS Managed k8s Clusters**.
   - **Blue-Green deployment** ensures minimal downtime and easy rollback.

## Key Benefits:

- Automated and consistent deployments.
- Reduced downtime with blue-green strategy.
- Scalable and reliable Kubernetes-based infrastructure.


