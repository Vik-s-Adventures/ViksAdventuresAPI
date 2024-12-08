graph TD
    A[**Cargar Dataset**<br>(Edad, Género, Respuestas)] --> B[**Aplicar Clustering**<br>(K-means)]
    B --> C[**Asignar IDs de Clusters**]
    C --> D[**Preparar Datos**<br>(Características y Etiquetas)]
    D --> E[**Entrenar Modelo**<br>(80% Datos)]
    
    E --> F[**Capa de Entrada**]
    F --> G[**Capa Oculta (64 neuronas)**]
    G --> H[**Capa Oculta (128 neuronas)**]
    H --> I[**Capa Oculta (64 neuronas)**]
    I --> J[**Capa de Salida (Softmax)**]
    
    style A fill:#lightblue,stroke:#000,stroke-width:2px
    style B fill:#lightgreen,stroke:#000,stroke-width:2px
    style C fill:#lightyellow,stroke:#000,stroke-width:2px
    style D fill:#lightcoral,stroke:#000,stroke-width:2px
    style E fill:#lightgray,stroke:#000,stroke-width:2px
    style F fill:#skyblue,stroke:#000,stroke-width:2px
    style G fill:#lightgreen,stroke:#000,stroke-width:2px
    style H fill:#lightgreen,stroke:#000,stroke-width:2px
    style I fill:#lightgreen,stroke:#000,stroke-width:2px
    style J fill:#salmon,stroke:#000,stroke-width:2px
