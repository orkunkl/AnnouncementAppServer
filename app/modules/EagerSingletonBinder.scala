package modules

import javax.inject.Inject

import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport
import services.database.DatabaseController
import services.schedulers.WebsiteUpdateScheduler

/**
  * Created by orkun on 25/02/17.
  */
class EagerSingletonBinder @Inject()(databaseController: DatabaseController) extends AbstractModule with AkkaGuiceSupport{
  override def configure(): Unit = {
    bind(classOf[WebsiteUpdateScheduler]).asEagerSingleton()
    databaseController.websites.map(websites =

  }
}
