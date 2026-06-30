package com.example.schedule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.schedule.ui.theme.ScheduleTheme

class MainActivity : ComponentActivity() {

    private lateinit var database: AppDatabase
    private lateinit var repository: ChapterRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = AppDatabase.getDatabase(this)
        repository = ChapterRepository(database.chapterDao())

        setContent {
            ScheduleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BookScreen(repository = repository)
                }
            }
        }
    }
}

@Composable
fun BookScreen(repository: ChapterRepository) {
    val viewModel: BookViewModel = viewModel(
        factory = BookViewModelFactory(repository)
    )

    val chapters by viewModel.chapters.collectAsState(initial = emptyList())

    // Группируем главы по категориям и книгам
    val groupedChapters = chapters.groupBy { it.bookCategory }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Заголовок с прогрессом
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "📖 Чтение Библии",
                    style = MaterialTheme.typography.headlineMedium
                )

                val readCount = chapters.count { it.isRead }
                val totalCount = chapters.size

                Text(
                    text = "Прочитано: $readCount из $totalCount глав",
                    style = MaterialTheme.typography.bodyMedium
                )

                LinearProgressIndicator(
                    progress = if (totalCount > 0) readCount.toFloat() / totalCount else 0f,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                )
            }
        }

        // Список глав
        if (chapters.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "📖 Нет глав",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { viewModel.addSampleData() }) {
                        Text("Загрузить план чтения Библии")
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Отображаем категории с группами
                groupedChapters.keys.forEach { category ->
                    // Заголовок категории
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Text(
                                text = category,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }

                    // Главы в категории
                    val categoryChapters = groupedChapters[category] ?: emptyList()
                    items(categoryChapters) { chapter ->
                        ChapterItem(
                            chapter = chapter,
                            onToggleRead = { viewModel.toggleReadStatus(chapter) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ChapterItem(
    chapter: Chapter,
    onToggleRead: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (chapter.isRead)
                MaterialTheme.colorScheme.secondaryContainer
            else
                MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "${chapter.bookName} ${chapter.chapterRange}",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Стр. ${chapter.pageNumber}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (chapter.isRead) {
                    Text(
                        text = "✅",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Checkbox(
                    checked = chapter.isRead,
                    onCheckedChange = { onToggleRead() }
                )
            }
        }
    }
}