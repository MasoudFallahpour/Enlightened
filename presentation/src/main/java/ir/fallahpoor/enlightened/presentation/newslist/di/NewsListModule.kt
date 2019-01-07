package ir.fallahpoor.enlightened.presentation.newslist.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ir.fallahpoor.enlightened.data.mapper.NewsEntityDataMapper
import ir.fallahpoor.enlightened.data.repository.Database
import ir.fallahpoor.enlightened.data.repository.NewsRepositoryImpl
import ir.fallahpoor.enlightened.data.repository.dao.NewsDao
import ir.fallahpoor.enlightened.data.repository.datasource.NewsDataSourceFactory
import ir.fallahpoor.enlightened.domain.interactor.GetNewsUseCase
import ir.fallahpoor.enlightened.domain.repository.NewsRepository
import ir.fallahpoor.enlightened.presentation.common.ExceptionParser
import ir.fallahpoor.enlightened.presentation.newslist.model.NewsListDataMapper
import ir.fallahpoor.enlightened.presentation.newslist.viewmodel.NewsListViewModelFactory

@Module
class NewsListModule {

    @Provides
    internal fun provideNewsViewModelFactory(
        getNewsUseCase: GetNewsUseCase, newsListDataMapper: NewsListDataMapper,
        exceptionParser: ExceptionParser
    ) = NewsListViewModelFactory(getNewsUseCase, newsListDataMapper, exceptionParser)

    @Provides
    internal fun provideNewsRepository(
        newsDao: NewsDao,
        newsDataSourceFactory: NewsDataSourceFactory,
        newsEntityDataMapper: NewsEntityDataMapper
    ): NewsRepository = NewsRepositoryImpl(newsDao, newsDataSourceFactory, newsEntityDataMapper)

    @Provides
    internal fun provideNewsDao(context: Context) = Database.getDatabase(context).newsDao()

}