package com.example.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BookScreen(viewModel: BookViewModel) {
    val chapters by viewModel.chapters.collectAsState(initial = emptyList())
    val stats by viewModel.stats.collectAsState()

    // Группируем по категориям
    val categories = chapters.groupBy { it.bookCategory }.keys.toList()
    val groupedChapters = chapters.groupBy { it.bookCategory }

    val pagerState = rememberPagerState(initialPage = 0)
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        // Верхний заголовок с прогрессом
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "📖 Чтение Библии",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    if (stats.totalChapters > 0) {
                        Text(
                            text = "${stats.readChapters}/${stats.totalChapters}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                if (stats.totalChapters > 0) {
                    LinearProgressIndicator(
                        progress = stats.progress,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                    )
                    Text(
                        text = "Прочитано: ${stats.readChapters} из ${stats.totalChapters} глав",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "Сегодня: ${stats.todayRead} глав",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        // Если нет глав - показываем кнопку загрузки
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
            // Табы с названиями категорий - упрощенная версия без индикатора
            ScrollableTabRow(
                selectedTabIndex = pagerState.currentPage,
                modifier = Modifier.fillMaxWidth(),
                edgePadding = 8.dp
            ) {
                categories.forEachIndexed { index, category ->
                    val shortName = when {
                        category.length > 15 -> category.take(12) + "…"
                        else -> category
                    }
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = { Text(shortName) }
                    )
                }
            }

            // Свайпаемые страницы с содержимым
            HorizontalPager(
                count = categories.size,
                state = pagerState,
                modifier = Modifier.fillMaxSize()
            ) { page ->
                val categoryName = categories[page]
                val categoryChapters = groupedChapters[categoryName] ?: emptyList()

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Заголовок с названием категории
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = categoryName,
                                    style = MaterialTheme.typography.headlineSmall
                                )
                                val readInCategory = categoryChapters.count { it.isRead }
                                val totalInCategory = categoryChapters.size
                                Text(
                                    text = "Прочитано: $readInCategory из $totalInCategory глав",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    items(categoryChapters) { chapter ->
                        ChapterItem(
                            chapter = chapter,
                            onToggleRead = { viewModel.toggleReadStatus(chapter) }
                        )
                    }
                }
            }

            // Кнопки навигации по разделам
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage > 0) {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        }
                    },
                    enabled = pagerState.currentPage > 0,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    Text(" Назад", modifier = Modifier.padding(start = 4.dp))
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            if (pagerState.currentPage < categories.size - 1) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        }
                    },
                    enabled = pagerState.currentPage < categories.size - 1,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        contentColor = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                ) {
                    Text("Вперед ")
                    Icon(Icons.Default.ArrowForward, contentDescription = "Вперед")
                }
            }

            // Индикатор страниц (точки внизу)
            if (categories.size > 1) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    repeat(categories.size) { index ->
                        val isSelected = pagerState.currentPage == index
                        Box(
                            modifier = Modifier
                                .size(if (isSelected) 12.dp else 8.dp)
                                .padding(horizontal = 4.dp)
                                .background(
                                    color = if (isSelected)
                                        MaterialTheme.colorScheme.primary
                                    else
                                        MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(50)
                                )
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